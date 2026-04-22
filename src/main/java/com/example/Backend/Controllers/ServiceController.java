package com.example.Backend.Controllers;

import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("auth/service")
public class ServiceController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public CompletableFuture<ResponseEntity<?>> getAllService(){
        try{
            return this.categoryService.getAllToMobile();
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }
}
