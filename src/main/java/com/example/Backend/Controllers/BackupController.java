package com.example.Backend.Controllers;

import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("/admin/backup")
public class BackupController {

    @Autowired
    private BackupService backUpService;

    @GetMapping("/manual")
    public CompletableFuture<ResponseEntity<?>> backupManual(){
        return this.backUpService.backupManual();
    }

    @GetMapping("/latest")
    public CompletableFuture<ResponseEntity<?>> backupLatest(){
        return this.backUpService.downloadLatest();
    }
}
