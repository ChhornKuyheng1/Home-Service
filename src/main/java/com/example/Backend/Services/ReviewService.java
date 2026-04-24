package com.example.Backend.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.Backend.DTOS.MobileProviders.ProviderViewDetails.Reviews.ReviewDetail;
import com.example.Backend.DTOS.Reviews.Providers.ProviderReview;
import com.example.Backend.Models.Booking;
import com.example.Backend.Models.Review;
import com.example.Backend.Models.ReviewImage;
import com.example.Backend.Repositories.BookingRepository;
import com.example.Backend.Repositories.ReviewImageRepository;
import com.example.Backend.Repositories.ReviewRepository;
import com.example.Backend.Responses.Messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewImageRepository reviewImageRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private Cloudinary cloudinary;

    private final ZoneId cambodiaZone = ZoneId.of("Asia/Phnom_Penh");

    private String getPublicIdFromUrl(String url) {
        String[] parts = url.split("/");
        String lastPart = parts[parts.length - 1];
        int dotIndex = lastPart.lastIndexOf('.');
        if (dotIndex > 0) {
            return lastPart.substring(0, dotIndex);
        }
        return lastPart;
    }


    @Async
    public CompletableFuture<ResponseEntity<?>> add(Long bookingId, String review,int rate, List<MultipartFile> image)throws Exception{
        Booking booking = this.bookingRepository.findById(bookingId).orElse(new Booking());
        if(!booking.getStatus().equals("Completed")) return CompletableFuture.completedFuture(ResponseEntity.status(409).body(new Message("Booking is not complete")));
        Review save = this.reviewRepository.save(new Review(booking,review,rate, LocalDate.now(cambodiaZone)));
        if(image!=null ){
            image.forEach(
                    file -> {
                        try {
                            this.reviewImageRepository.save(new ReviewImage(save, cloudinary.url().transformation(new Transformation()
                                            .width(600)
                                            .height(600)
                                            .crop("fill")
                                           )
                                            .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString()),
                                    LocalDate.now(cambodiaZone))
                            );
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            this.notificationService.sendMessage(booking.getJobFocus().getJob().getProvider().getUser(),"Review","Your have a review","PROVIDER/BOOKING",bookingId);
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        }
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> response(Long reviewId,String message){
       try{
           Review review = this.reviewRepository.findById(reviewId).orElse(null);
           if(review == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
           review.setResponse(message);
           this.reviewRepository.save(review);
           return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
       } catch (Exception e) {
           return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
       }
    }


    public ProviderReview getByBookingId(Long id){
        Review review = this.reviewRepository.findById(id).orElse(new Review());
        return new ProviderReview(
                review.getId(),
                review.getRate(),
                review.getReview(),
                this.reviewImageRepository.findByReviewId(review.getId()),
                review.getResponse()
        );
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getReviewToProviderDetail(Long providerId){
        List<ReviewDetail> reviewDetails = new ArrayList<>();
        this.reviewRepository.findByProviderId(providerId).forEach(
                review -> {
                    reviewDetails.add(
                            new ReviewDetail(
                                    review.getBooking().getUser().getName(),
                                    review.getBooking().getJobFocus().getJob().getService().getName(),
                                    review.getRate(),
                                    review.getReview(),
                                    this.reviewImageRepository.findByReviewId(review.getId()),
                                    review.getBooking().getUser().getProfile(),
                                    review.getDate()
                            )
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(reviewDetails));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getToJobFocusId(Long jobFocusId){
        try{
            List<ReviewDetail> reviewDetails = new ArrayList<>();
            this.reviewRepository.findByJobFocusId(jobFocusId).forEach(
                    review -> {
                        reviewDetails.add(
                                new ReviewDetail(
                                        review.getBooking().getUser().getName(),
                                        review.getBooking().getJobFocus().getJob().getService().getName(),
                                        review.getRate(),
                                        review.getReview(),
                                        this.reviewImageRepository.findByReviewId(review.getId()),
                                        review.getBooking().getUser().getProfile(),
                                        review.getDate()
                                )
                        );
                    }
            );
            return CompletableFuture.completedFuture(ResponseEntity.ok(reviewDetails));
        }catch (Exception e){
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }
}
