package com.example.Backend.Services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.Backend.DTOS.Portfolios.PortfolioDto;
import com.example.Backend.Models.Portfolio;
import com.example.Backend.Models.Provider;
import com.example.Backend.Repositories.PortfolioRepository;
import com.example.Backend.Responses.Messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioRepository portfolioRepository;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private Cloudinary cloudinary;

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
    public CompletableFuture<ResponseEntity<?>> add(Long providerId, MultipartFile file) {
       try{
           Provider provider = this.providerService.getById(providerId);
           if(file==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No file to create")));
           this.portfolioRepository.save(
                   new Portfolio(
                           provider,
                           cloudinary.url().transformation(new Transformation()
                                           .width(600)
                                           .height(600)
                                           .crop("fill")
                                           )
                                           .generate(cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("public_id").toString()),
                           LocalDate.now()
                   )
           );
           return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
       } catch (Exception e) {
           return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
       }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> delete(Long id) {
        try{
            Portfolio portfolio = this.portfolioRepository.findById(id).orElse(null);
            if(portfolio==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
            cloudinary.uploader().destroy(getPublicIdFromUrl(portfolio.getImage()), ObjectUtils.emptyMap());
            this.portfolioRepository.delete(portfolio);
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> getPortfolioByProviderId(Long providerId){
        List<PortfolioDto> portfolioDtoList = new ArrayList<>();
        this.portfolioRepository.findByProvider_Id(providerId).forEach(
                portfolio -> {
                    portfolioDtoList.add(
                            new PortfolioDto(portfolio.getId(),portfolio.getImage())
                    );
                }
        );
        return CompletableFuture.completedFuture(ResponseEntity.ok(portfolioDtoList));
    }
}
