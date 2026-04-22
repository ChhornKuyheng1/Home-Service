package com.example.Backend.Controllers;

import com.example.Backend.Requests.BookingRequest;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("user/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/add")
    public CompletableFuture<ResponseEntity<?>> add(@ModelAttribute BookingRequest booking){
        try{
            return this.bookingService.add(booking);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PostMapping("/check")
    public ResponseEntity<?> check(@RequestParam LocalDate date,@RequestParam Long jobId){
        try{
            return ResponseEntity.ok(this.bookingService.getAvailableSlots(date,jobId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Message(e.getMessage()));
        }
    }

    @GetMapping("/provider/all")
    public CompletableFuture<ResponseEntity<?>> getAllBookingByProviderId(@RequestParam Long providerId){
        try{
            return this.bookingService.getAllBookingByProviderId(providerId,null);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/all/today")
    public CompletableFuture<ResponseEntity<?>> getAllBookingByProviderIdToDay(@RequestParam Long providerId){
        try{
            return this.bookingService.getAllBookingByProviderId(providerId,LocalDate.now().plusDays(1));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/all/tomorrow")
    public CompletableFuture<ResponseEntity<?>> getAllBookingByProviderIdTomorrow(@RequestParam long providerId){
        try{
            return this.bookingService.getAllBookingByProviderId(providerId,LocalDate.now().plusDays(1));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/request/all")
    public CompletableFuture<ResponseEntity<?>> getAllBookingRequestByProviderId(@RequestParam Long providerId){
        try{
            return this.bookingService.getAllRequestByProviderId(providerId,null);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }


    @GetMapping("/provider/request/by/date")
    public CompletableFuture<ResponseEntity<?>> getAllBookingRequestTomorrowByProviderId(@RequestParam Long providerId,@RequestParam LocalDate date){
        try{
            return this.bookingService.getAllRequestByProviderId(providerId,date);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/upcoming")
    public CompletableFuture<ResponseEntity<?>> getAllUpcomingByProviderId(@RequestParam Long providerId){
        try{
            return this.bookingService.getAllUpcomingByProviderId(providerId,null);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/upcoming/by/date")
    public CompletableFuture<ResponseEntity<?>> getAllUpcomingByDate(@RequestParam Long providerId,@RequestParam LocalDate date){
        try{
            return this.bookingService.getAllUpcomingByProviderId(providerId,date);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/upcoming/today")
    public CompletableFuture<ResponseEntity<?>> getAllUpcomingTodayByProviderId(@RequestParam Long providerId){
        try{
            return this.bookingService.getAllUpcomingByProviderId(providerId,LocalDate.now());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/upcoming/tomorrow")
    public CompletableFuture<ResponseEntity<?>> getAllUpcomingTomorrowByProviderId(@RequestParam Long providerId){
        try{
            return this.bookingService.getAllUpcomingByProviderId(providerId,LocalDate.now().plusDays(1));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/inProgress")
    public CompletableFuture<ResponseEntity<?>> getAllInProgressByProviderId(@RequestParam Long providerId){
        try{
            return this.bookingService.getAllInProgress(providerId,null);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/inProgress/search")
    public CompletableFuture<ResponseEntity<?>> getAllInProgressByProviderId(@RequestParam Long providerId,@RequestParam Long cateId){
        try {
            return this.bookingService.getAllInProgress(providerId,cateId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/completed")
    public CompletableFuture<ResponseEntity<?>> getAllCompleteByProviderId(@RequestParam Long providerId){
        try{
            return this.bookingService.getCompleteByProviderId(providerId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/cancelled")
    public CompletableFuture<ResponseEntity<?>> getAllCancelledByProviderId(@RequestParam Long providerId){
        try{
            return this.bookingService.getCancelledByProviderId(providerId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/view")
    public CompletableFuture<ResponseEntity<?>> getBookingViewToProvider(@RequestParam Long bookingId){
            return this.bookingService.getBookingViewToProvider(bookingId);
    }

    @PutMapping("/completed")
    public CompletableFuture<ResponseEntity<?>> completeBooking(@RequestParam Long bookingId,@RequestParam double price){
        try{
            return this.bookingService.completeBooking(bookingId,price);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/upcoming")
    public  CompletableFuture<ResponseEntity<?>> setUpcomingStatus(@RequestParam Long bookingId){
        try{
            return this.bookingService.setUpcomingStratus(bookingId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/inProgress")
    public  CompletableFuture<ResponseEntity<?>>setInProgressStatus(@RequestParam Long bookingId){
        try{
            return this.bookingService.setInProgressStatus(bookingId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/cancel")
    public  CompletableFuture<ResponseEntity<?>> setInCancelledStatus(@RequestParam Long bookingId){
        try{
            return this.bookingService.setCancelledStatus(bookingId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/user/all")
    public CompletableFuture<ResponseEntity<?>> getAllByUserId(@RequestParam Long userId){
        try{
            return this.bookingService.getAllBookingByUserId(userId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/user/request")
    public CompletableFuture<ResponseEntity<?>> getAllByUserIdAndRequest(@RequestParam Long userId){
        try{
            return this.bookingService.getAllBookingByUserIdAndStatus(userId,"Requested","bookings.booking_date DESC");
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/user/upcoming")
    public CompletableFuture<ResponseEntity<?>> getAllByUserIdAndUpcoming(@RequestParam Long userId){
        try{
            return this.bookingService.getAllBookingByUserIdAndStatus(userId,"Upcoming","bookings.work_date ASC , bookings.work_time ASC");
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/user/inprogress")
    public CompletableFuture<ResponseEntity<?>> getAllByUserIdAndInprogress(@RequestParam Long userId){
        try{
            return this.bookingService.getAllBookingByUserIdAndStatus(userId,"In Progress","bookings.work_date ASC , bookings.work_time ASC");
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/user/completed")
    public CompletableFuture<ResponseEntity<?>> getAllByUserIdAndCompleted(@RequestParam Long userId){
        try{
            return this.bookingService.getAllBookingByUserIdAndStatus(userId,"Completed","bookings.complete_date DESC ");
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/user/cancelled")
    public CompletableFuture<ResponseEntity<?>> getAllByUserIdAndCancelled(@RequestParam Long userId){
        try{
            return this.bookingService.getAllBookingByUserIdAndStatus(userId,"Cancelled","bookings.booking_date DESC");
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/user/view")
    public CompletableFuture<ResponseEntity<?>> getUserBookingView(@RequestParam Long bookingId){
        try{
            return this.bookingService.getUserBookingView(bookingId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/user/cancelled")
    public CompletableFuture<ResponseEntity<?>> userCancelled(@RequestParam Long bookingId){
        try{
            return this.bookingService.setCancelledStatusFoUser(bookingId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/check/booking/time")
    public CompletableFuture<ResponseEntity<?>> checkBookingTime(@RequestParam LocalDate date,@RequestParam Long jobFocusId){
        try{
            return this.bookingService.getAvailableSlots(date,jobFocusId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }


}
