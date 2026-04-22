package com.example.Backend.Utils;

import com.example.Backend.DTOS.TimeDto;
import com.example.Backend.Models.Booking;
import com.example.Backend.Models.JobFocus;
import com.example.Backend.Models.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class CheckBookingTime {

    private static final Logger log = LoggerFactory.getLogger(CheckBookingTime.class);
    private  static Map<LocalTime, JobFocus> workTimes = new ConcurrentHashMap<>();

    @Async
    private static void  addBookingToWorkTime(List<Booking> bookings){
        workTimes.clear();
        bookings.forEach(
                booking -> {
                    workTimes.put(booking.getWorkTime(),booking.getJobFocus());
                }
        );
    }

    @Async
    public static CompletableFuture<ResponseEntity<?>> checkTimeToWork(List<Booking> bookings,JobFocus jobFocus,LocalDate date){
        List<TimeDto> times = new ArrayList<>();
        Provider provider = jobFocus.getJob().getProvider();
        LocalTime startTime = provider.getStartTime();
        LocalTime endTime = provider.getEndTime();
        if(
                date.getMonthValue()<=LocalDate.now().getMonthValue() &&
                        date.getDayOfYear()<LocalDate.now().getDayOfYear() &&
                        date.getYear()==LocalDate.now().getYear()
        ) return CompletableFuture.completedFuture(ResponseEntity.ok(times));
        if(bookings.size()>0){
            addBookingToWorkTime(bookings);
            for(int i = startTime.getHour();i<endTime.getHour();i++){
                LocalTime startWork =startTime.plusHours(jobFocus.getDuration());
                LocalTime t = startTime.minusMinutes(startTime.getMinute());
                if(workTimes.containsKey(t)){
                    startTime = startTime.plusHours(workTimes.get(t).getDuration());
                }
                else if(workTimes.containsKey(startTime)){
                    startTime = startTime.plusHours(workTimes.get(startTime).getDuration());
                }
                else {
                    boolean check = true;
                    for (int j = 1;j<=jobFocus.getDuration();j++){
                        if(workTimes.containsKey(startTime.plusHours(j))){
                            if(j==jobFocus.getDuration()){
                                if (startWork.getHour() > endTime.getHour()) break;
                                if(date.getMonthValue()<=LocalDate.now().getMonthValue() && date.getDayOfYear() != LocalDate.now().getDayOfYear()){
                                    times.add(
                                            new TimeDto(startTime,startTime.format(DateTimeFormatter.ofPattern("hh:mm a")))
                                    );
                                } else if (LocalTime.now().getHour() < startTime.getHour()) {
                                    times.add(
                                            new TimeDto(startTime,startTime.format(DateTimeFormatter.ofPattern("hh:mm a")))
                                    );
                                }
                                startTime = startWork;
                            }
                            startTime = startWork.plusHours(j);
                            check = false ;
                            break;
                        }
                    }
                    if(check){
                        if (startWork.getHour() > endTime.getHour()) break;
                        if(date.getMonthValue()<=LocalDate.now().getMonthValue() && date.getDayOfYear() != LocalDate.now().getDayOfYear()){
                            times.add(
                                    new TimeDto(startTime,startTime.format(DateTimeFormatter.ofPattern("hh:mm a")))
                            );

                        } else if (LocalTime.now().getHour() < startTime.getHour()) {
                            times.add(
                                    new TimeDto(startTime,startTime.format(DateTimeFormatter.ofPattern("hh:mm a")))
                            );
                        }
                        startTime = startWork;
                    }
                }
            }
            return CompletableFuture.completedFuture(ResponseEntity.ok(times));
        }
        for (int i = startTime.getHour();i<=endTime.getHour();i++){
           LocalTime startWork =startTime.plusHours(jobFocus.getDuration());
           if(startWork.getHour()>endTime.getHour() &&startWork.getMinute()>endTime.getMinute() ){ break;}
           if(date.getMonthValue()<=LocalDate.now().getMonthValue() && date.getDayOfYear() != LocalDate.now().getDayOfYear()){
               times.add(
                       new TimeDto(startTime,startTime.format(DateTimeFormatter.ofPattern("hh:mm a")))
               );

           } else if (LocalTime.now().getHour() < startTime.getHour()) {
               times.add(
                       new TimeDto(startTime,startTime.format(DateTimeFormatter.ofPattern("hh:mm a")))
               );
           }
            startTime = startWork;
        }
        return CompletableFuture.completedFuture(ResponseEntity.ok(times));
    }

}
