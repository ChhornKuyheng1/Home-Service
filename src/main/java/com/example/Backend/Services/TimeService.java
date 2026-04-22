package com.example.Backend.Services;

import com.example.Backend.DTOS.*;
import com.example.Backend.Models.JobFocus;
import com.example.Backend.Models.Provider;
import com.example.Backend.Repositories.BookingRepository;
import com.example.Backend.Repositories.JobFocusRepository;
import com.example.Backend.Responses.Messages.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TimeService {
    private static final Logger log = LoggerFactory.getLogger(TimeService.class);
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private JobFocusRepository jobFocusRepository;

    private Map<LocalTime, JobFocus> workTimes = new ConcurrentHashMap<>();

    private Map<LocalDate, Long> bookings = new ConcurrentHashMap<>();

    private Map<LocalDate, BookingJob> bookingJobs = new ConcurrentHashMap<>();

    private Map<String, String> bookingTime = new ConcurrentHashMap<>();

    private Map<LocalTime, Long> overTime = new ConcurrentHashMap<>();

    private Map<LocalTime, Long> noAvaileble = new ConcurrentHashMap<>();
    
    private Map<LocalDate,Map<LocalTime,Long>> checkDateAndTime = new ConcurrentHashMap<>();
    
    

    @Async
    private void mapBookingJobs(Long providerId) {
        this.bookingJobs.clear();
        this.bookingRepository.findWorkDateAndAndDateAndDuration(providerId).forEach(
                bookingJob -> {
                    this.bookingJobs.put(bookingJob.getDate(), new BookingJob(bookingJob.getDate(), bookingJob.getWorkTime(), bookingJob.getDuration()));
                }
        );
    }

    @Async
    private void mapNoAvailable(Long providerId, LocalDate date) {
        this.noAvaileble.clear();
        this.bookingRepository.findByProviderIdAndWorkDate(providerId, date).forEach(
                booking -> {
                    this.noAvaileble.put(booking.getWorkTime(), booking.getJobFocus().getDuration());
                }
        );
    }

    @Async
    private void mapBooking(Long jobId) {
        this.bookings.clear();
        JobFocus job = this.jobFocusRepository.findById(jobId).orElse(new JobFocus());
        this.bookingRepository.sumBookingTimeByWorkDate(job.getJob().getProvider().getId()).forEach(
                bookingDate -> {
                    this.bookings.put(bookingDate.getWorkDate(), bookingDate.getTime());
                }
        );
    }

    @Async
    private void mapBookingTime(LocalDate date, Long jobId) {
        this.workTimes.clear();
        JobFocus job = this.jobFocusRepository.findById(jobId).orElse(new JobFocus());
        this.bookingRepository.findByProviderIdAndWorkDate(job.getJob().getProvider().getId(), date).forEach(
                booking -> {
                    this.workTimes.put(booking.getWorkTime(), booking.getJobFocus());
                }
                
        );

    }

    @Async
    private void mapBookingWorkDate(Long providerId){
        this.checkDateAndTime.clear();
        this.bookingRepository.findBookingByWorkDate(providerId).forEach(
                date -> {
                   if(this.checkDateAndTime.containsKey(date.getDate())){
                       this.checkDateAndTime.get(date.getDate()).put(date.getTime(),date.getDuration());
                   }else {
                       Map<LocalTime, Long> timeMap = new ConcurrentHashMap<>();
                       timeMap.put(date.getTime(), date.getDuration());
                       this.checkDateAndTime.put(date.getDate(), timeMap);
                   }
                }
        );
    }

    private AvailableAndEndTime checkto(LocalTime start, LocalTime end, LocalDate bookingDate, long maxTime,Provider provider) {
        LocalTime starTime = start;

        LocalTime endTime = end;

        AvailableAndEndTime availableAndEndTime = new AvailableAndEndTime(false,endTime);

        while (starTime.isBefore(endTime)){

            long day = maxTime / Duration.between(starTime,endTime).toHours();

            for (long i = 0 ; i<=day ; i++){

                LocalDate d = bookingDate.minusDays(i);

                if(this.checkDateAndTime.containsKey(d)) {
                    if (this.checkDateAndTime.get(d).containsKey(starTime)) {
                        long work = this.checkDateAndTime.get(d).get(starTime);

                        if (work >= Duration.between(provider.getStartTime(), provider.getEndTime()).toHours()) {

                            LocalTime s = starTime.plusHours(1);

                            LocalTime e = endTime;

                            while (s.isBefore(e)) {

                                if (this.checkDateAndTime.get(d).containsKey(s)) {
                                    e = s;
                                    break;
                                }

                                s = s.plusHours(1);
                            }

                            long du = Duration.between(starTime, e).toHours();

                            long workTo = work / du;

                            if (!d.plusDays(workTo - 1).isBefore(bookingDate) || d.plusDays(workTo - 1).isBefore(bookingDate)) {
                                return new AvailableAndEndTime(true, starTime);
                            }


                        }
                    }
                }
            }

            starTime = starTime.plusHours(1);
        }


        return availableAndEndTime;

    }

    private AvailableAndEndTime checkOnAvailable(LocalDate bookDate,LocalTime start,LocalTime end,Provider provider,long maxDuration){
        LocalTime starTime = provider.getStartTime();
        LocalTime endTime = end;
        if(starTime.equals(endTime)){
            return new AvailableAndEndTime(false,null);
        }
        long providerWork = Duration.between(provider.getStartTime(), provider.getEndTime()).toHours();

        long day = maxDuration / Duration.between(provider.getStartTime(),end).toHours();

        for(long i = 0 ; i <= day ; i++){

            LocalDate currentDate = bookDate.minusDays(i);

            if(this.checkDateAndTime.containsKey(currentDate)) {

               if (this.checkDateAndTime.get(currentDate).containsKey(starTime)) {

                   long duration =  this.checkDateAndTime.get(currentDate).get(starTime);

                   if (duration > providerWork) {

                       long d = duration / Duration.between(starTime, endTime).toHours();

                       if (!currentDate.plusDays(d).isBefore(bookDate)) {

                           return new AvailableAndEndTime(true, starTime);
                       }

                   }
               }
            }
        }

        return new AvailableAndEndTime(false,null);
    }

    private AvailableAndEndTime isTimeAvailable(LocalDate bookingDate , LocalTime start , LocalTime end , long maxDuration , Provider provider ){

        long providerWorkTime = Duration.between(start,end).toHours();

        long maxDu =maxDuration / providerWorkTime;

        for(long i =1 ; i <= maxDu ; i++){

            LocalDate cu = bookingDate.minusDays(i);

            if(this.checkDateAndTime.containsKey(cu)){

                AvailableAndEndTime availableAndEndTime = this.checkto(start,end,bookingDate,maxDuration,provider);

                if(availableAndEndTime.isAvailable()) return new AvailableAndEndTime(true,availableAndEndTime.getEnd());

            }
        }

        return new AvailableAndEndTime(false,null);
    }

    private AvailableTime availableTime (Provider provider , LocalDate date){

        LocalTime starTime = provider.getStartTime();
        LocalTime endTime = provider.getEndTime();
        long workDuration = Duration.between(starTime,endTime).toHours();
        long maxDuration = this.jobFocusRepository.findByMaxDuration(provider.getId());

        AvailableTime availableTime = new AvailableTime(false,0);

        if(maxDuration > workDuration){

            while (starTime.isBefore(endTime)){

                long day = maxDuration / Duration.between(starTime,endTime).toHours();

                for(long i = 0 ; i <= day ; i++){

                    LocalDate currentDate = date.minusDays(i);

                    if(!this.bookingJobs.containsKey(currentDate)) continue;

                    BookingJob bookingJob = this.bookingJobs.get(currentDate);

                    LocalTime startWork = bookingJob.getWorkTime();

                    if(bookingJob.getDuration() > workDuration){

                        AvailableAndEndTime availableAndEndTimeTo = this.checkto(startWork.plusHours(1),provider.getEndTime(),currentDate,maxDuration,provider);

                        long durationToWork = Duration.between(startWork,availableAndEndTimeTo.getEnd()).toHours();

                        long workDay = bookingJob.getDuration() / durationToWork;

                        if(!currentDate.plusDays(workDay-1).isBefore(date)){


                            if(!endTime.equals(availableAndEndTimeTo.getEnd())) {
                                AvailableAndEndTime availableAndEndTime = this.isTimeAvailable(date,availableAndEndTimeTo.getEnd(),endTime,maxDuration,provider);
                                if(availableAndEndTime.isAvailable()){
                                    return new AvailableTime(true,0);
                                }
                                return new AvailableTime(true,availableAndEndTimeTo.getEnd().getHour());
                            }

                            AvailableAndEndTime availableAndEndTime = this.checkOnAvailable(date,starTime,startWork,provider,maxDuration);
                            if(availableAndEndTime.isAvailable()) {
                                return new AvailableTime(true,0);
                            }

                            return new AvailableTime(false,startWork.getHour());
                        }

                        if(currentDate.plusDays(workDay-1).equals(date.minusDays(1))){

                            long time = bookingJob.getDuration() % durationToWork;

                            if(time!=0)  this.overTime.put(bookingJob.getWorkTime(),time);

                        }
                    }

                }

                starTime = starTime.plusHours(1);

            }
        }

        return availableTime;
    }

    private boolean checkNoTime(LocalTime start , LocalTime endTime,LocalDate date){
        while (start.isBefore(endTime)){

            if(!this.checkDateAndTime.get(date).containsKey(start)){
                start = start.plusHours(1);
                continue;
            }

            if(start.plusHours(this.checkDateAndTime.get(date).get(start)).isBefore(endTime)
                    || start.plusHours(this.checkDateAndTime.get(date).get(start)).equals(endTime)){
                return false;
            }

            return true;

        }

        return false;
    }

    private AvailableAndEndTime checkNextDayAvailable(LocalTime starTime , LocalTime endTime , long duration , LocalDate date, Long providerId, LocalTime providerStart,LocalTime providerEnd){
        LocalTime start = starTime;
        LocalTime end = endTime;
        long day = duration / Duration.between(starTime,providerEnd).toHours();
        long h = Duration.between(providerStart,providerEnd).toHours()/2;
        boolean isAvailable = false;
        for(long i = 1 ; i <= day ; i++){
            LocalDate currentDate = date.plusDays(i);
            LocalTime goToStart = start;
            LocalTime toEnd = end;

            if(this.checkDateAndTime.containsKey(currentDate)) {

                long w = 0;

                while (goToStart.isBefore(toEnd)) {

                    w++;

                    if (this.checkNoTime(providerStart, goToStart, currentDate))
                        return new AvailableAndEndTime(true, null);

                    if (!this.checkDateAndTime.get(currentDate).containsKey(goToStart)) {

                        goToStart = goToStart.plusHours(1);

                        continue;
                    }

                    if (w >= h) {

                        end = goToStart;

                        break;
                    }

                    return new AvailableAndEndTime(true, null);


                }
            }

            long w = 0;

            while (goToStart.isBefore(toEnd)){

                w++;

                goToStart = goToStart.plusHours(1);

            }

            if(w >= h){

                isAvailable = true;

                continue;
            }

            isAvailable = false;

        }

        if(isAvailable){ return new AvailableAndEndTime(false,end);}

        return new AvailableAndEndTime(true,null);
    }

    private List<TimeDto> durationIsGreaterThanProviderWorkTime(long duration , Provider provider , LocalDate workDate,LocalTime providerEnd){

        List<TimeDto> times = new ArrayList<>();

        LocalTime starTime = provider.getStartTime();

        LocalTime endTime = provider.getEndTime();

        while (starTime.isBefore(endTime)){

                if (LocalDate.now().equals(workDate)) {
                    if (!starTime.isAfter(LocalTime.now())) {
                        starTime = starTime.plusHours(1);
                        continue;
                    }
                }

                if(this.workTimes.containsKey(starTime)){
                    starTime = starTime.plusHours(this.workTimes.get(starTime).getDuration());
                    continue;
                }else {
                    if(this.overTime.containsKey(starTime)){
                        starTime = starTime.plusHours(this.overTime.get(starTime));
                        continue;
                    }
                }

                LocalTime end = endTime;
                LocalTime start = starTime;
                while (start.isBefore(endTime)){

                    if(!this.workTimes.containsKey(start)){
                        start = start.plusHours(1);
                        continue;
                    }
                    end = start;
                    break;
                }

                long durationToWork = duration - Duration.between(starTime,endTime).toHours();

                AvailableAndEndTime availableAndEndTime = this.checkNextDayAvailable(starTime,end,durationToWork,workDate,provider.getId(),provider.getStartTime(),providerEnd);

                if(availableAndEndTime.isAvailable()){
                    starTime = starTime.plusHours(1);
                    continue;
                }

                times.add(new TimeDto(starTime,starTime.format(DateTimeFormatter.ofPattern("hh:mm a"))+"-"+availableAndEndTime.getEnd().format(DateTimeFormatter.ofPattern("hh:mm a"))));

                starTime = starTime.plusHours(1);
        }

        return times;
    }

    private List<TimeDto> durationIsLessThanProviderWorkTime(long duration , Provider provider ,LocalDate date){

        List<TimeDto> times = new ArrayList<>();

        LocalTime starTime = provider.getStartTime();

        LocalTime endTime = provider.getEndTime();

        if(starTime.equals(endTime)) return times;

        if(!this.workTimes.isEmpty()){

            LocalTime workTime = starTime;

            while (workTime.isBefore(endTime)){

                if (LocalDate.now().equals(date)) {
                    if (!workTime.isAfter(LocalTime.now())) {
                        workTime = workTime.plusHours(1);
                        continue;
                    }
                }

                if(this.workTimes.containsKey(workTime)){

                    workTime = workTime.plusHours(this.workTimes.get(workTime).getDuration());

                    continue;
                }else {
                    if(this.overTime.containsKey(workTime)){
                        workTime = workTime.plusHours(this.overTime.get(workTime));
                        continue;
                    }
                }

                boolean a =false;

                for (long j = 1 ; j <= duration ; j++) {

                    if (this.workTimes.containsKey(workTime.plusHours(j))) {

                        if (j == duration) {

                            if(workTime.plusHours(duration).equals(endTime)){
                                times.add(new TimeDto(workTime, workTime.format(DateTimeFormatter.ofPattern("hh:mm a"))));
                                a=true;
                                break;
                            }

                            times.add(new TimeDto(workTime, workTime.format(DateTimeFormatter.ofPattern("hh:mm a"))));

                            a=true;

                            break;
                        }
                        a = true;
                        break;
                    }
                }

                if(a) {
                    workTime = workTime.plusHours(1);
                    continue;
                }

                if(workTime.plusHours(duration).equals(endTime)){
                    times.add(new TimeDto(workTime,workTime.format(DateTimeFormatter.ofPattern("hh:mm a"))));
                    break;
                }

                times.add(new TimeDto(workTime,workTime.format(DateTimeFormatter.ofPattern("hh:mm a"))));

                workTime = workTime.plusHours(1);
            }

            return times;
        }

       LocalTime workTime = starTime;

       while (workTime.isBefore(endTime)) {

           if (LocalDate.now().equals(date)) {
               if (!workTime.isAfter(LocalTime.now())) {
                   workTime = workTime.plusHours(1);
                   continue;
               }
           }

           if(this.overTime.containsKey(workTime)){
               workTime = workTime.plusHours(this.overTime.get(workTime));
               continue;
           }

           if(workTime.plusHours(duration).getHour()>endTime.getHour())break;

           if(workTime.plusHours(duration).equals(endTime)){
               times.add(new TimeDto(
                       workTime,
                       workTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
               ));
               break;
           }

           times.add(new TimeDto(
                   workTime,
                   workTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
           ));

           workTime = workTime.plusHours(1);
       }

        return times;
   }

   @Async
   public CompletableFuture<ResponseEntity<?>> getTime(LocalDate date , Provider provider , JobFocus jobFocus){
        try{

            List<TimeDto> times = new ArrayList<>();

            this.overTime.clear();

            this.mapBookingTime(date,jobFocus.getId());

            this.mapBooking(jobFocus.getId());

            this.mapBookingJobs(provider.getId());

            this.mapBookingWorkDate(provider.getId());

            AvailableTime availableTime = this.availableTime(provider,date);

            LocalTime providerEnd = provider.getEndTime();

            if(availableTime.isAvailable()){
                if(availableTime.getTime()==0){
                    return CompletableFuture.completedFuture(ResponseEntity.ok(times));
                }
                provider.setStartTime(LocalTime.of((int)availableTime.getTime(),provider.getStartTime().getMinute()));
            }else {
                if(availableTime.getTime()!=0) provider.setEndTime(LocalTime.of((int)availableTime.getTime(),provider.getEndTime().getMinute()));
            }

            if(jobFocus.getDuration() > Duration.between(provider.getStartTime(),provider.getEndTime()).toHours()){

                return CompletableFuture.completedFuture(
                        ResponseEntity.ok(
                                this.durationIsGreaterThanProviderWorkTime(jobFocus.getDuration(),provider,date,providerEnd)
                        )
                );
            }

            return CompletableFuture.completedFuture(ResponseEntity.ok(this.durationIsLessThanProviderWorkTime(jobFocus.getDuration(),provider,date)));

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
   }

}
