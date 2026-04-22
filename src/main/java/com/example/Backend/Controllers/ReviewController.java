package com.example.Backend.Controllers;

import com.example.Backend.Requests.ReviewRequest;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("user/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public CompletableFuture<ResponseEntity<?>> add(@ModelAttribute ReviewRequest review){
        try{
            return this.reviewService.add(review.getBookingId(),review.getReview(),review.getRate(),review.getImage());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/provider/detail")
    public CompletableFuture<ResponseEntity<?>> getToProviderDetail(@RequestParam Long providerId){
        try{
            return this.reviewService.getReviewToProviderDetail(providerId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("job/focus/id")
    public CompletableFuture<ResponseEntity<?>> getByJobFocusId(@RequestParam Long jobFocusId){
        return this.reviewService.getToJobFocusId(jobFocusId);
    }

    @PutMapping("/provider/response")
    public CompletableFuture<ResponseEntity<?>> response(@RequestParam Long reviewId,@RequestParam String response){
        return this.reviewService.response(reviewId,response);
    }
}
