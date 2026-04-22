package com.example.Backend.Services;

import com.example.Backend.Responses.Messages.Message;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;

@Service
public class BackupService {

    @Async
    @Scheduled(cron = "0 0 0 * * ?")
    private void backUp() {
        try {

            String date =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));

            String fileName ="backup_"+ System.currentTimeMillis() +".sql";

            String path = "Backup_Autos/" + fileName;

            ProcessBuilder pb = new ProcessBuilder(
                    "E:/MySql/bin/mysqldump.exe",
                    "-u", "root",
                    "-p12345",
                    "camfix_db",
                    "-r", path
            );

            pb.redirectErrorStream(true);

            Process process = pb.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Backup successful: " + fileName);
            } else {
                System.out.println("Backup failed");
            }

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> downloadLatest(){
        try{
            File folder = new File("Backup_Autos");

            File[] files = folder.listFiles((dir, name) -> name.endsWith(".sql"));

            if (files == null || files.length == 0) {
                return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No File Backup")));
            }

            // ✅ Find latest file
            File latestFile = Arrays.stream(files)
                    .max(Comparator.comparingLong(File::lastModified))
                    .orElseThrow(() -> new RuntimeException("No file found"));

            Path path = latestFile.toPath();
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new RuntimeException("File not readable");
            }

            return CompletableFuture.completedFuture(
                    ResponseEntity.ok()
                            .contentType(MediaType.APPLICATION_OCTET_STREAM)
                            .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + resource.getFilename() + "\"")
                            .body(resource
                            )
            );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> backupManual(){
        try{

            String date =LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a"));

            String fileName ="backup_"+System.currentTimeMillis() +".sql";

            String path = "backups/" + fileName;

            ProcessBuilder pb = new ProcessBuilder(
                    "E:/MySql/bin/mysqldump.exe",
                    "-u", "root",
                    "-p12345",
                    "camfix_db",
                    "-r", path
            );

            pb.redirectErrorStream(true);

            Process process = pb.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Backup successful: " + fileName);
                return CompletableFuture.completedFuture(this.downloadManual(fileName));
            } else {
                System.out.println("Backup failed");
            }
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message("Service is Error please try again")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    private ResponseEntity<?> downloadManual(String fileName){
       try{
           Path filePath = Paths.get("backups").resolve(fileName).normalize();

           Resource resource = new UrlResource(filePath.toUri());

           // ✅ Check file exist
           if (!resource.exists() || !resource.isReadable()) {
               throw new RuntimeException("File not found: " + fileName);
           }

           return ResponseEntity.ok()
                   .contentType(MediaType.APPLICATION_OCTET_STREAM)
                   .header(HttpHeaders.CONTENT_DISPOSITION,
                           "attachment; filename=\"" + resource.getFilename() + "\"")
                   .body(resource);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

}
