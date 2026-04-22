package com.example.Backend.Controllers;

import com.example.Backend.Requests.PortfolioRequest;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("user/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/add")
    public CompletableFuture<ResponseEntity<?>> add(@ModelAttribute PortfolioRequest portfolio){
        try{
            return this.portfolioService.add(portfolio.getProviderId(),portfolio.getImage());
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @DeleteMapping("/delete")
    public CompletableFuture<ResponseEntity<?>> delete(@RequestParam Long portfolioId){
        try{
            return this.portfolioService.delete(portfolioId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/get/by/provider/id")
    public CompletableFuture<ResponseEntity<?>> getByProviderId(@RequestParam Long providerId){
        try{
            return this.portfolioService.getPortfolioByProviderId(providerId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }
}
