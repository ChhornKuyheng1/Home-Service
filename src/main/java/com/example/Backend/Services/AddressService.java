package com.example.Backend.Services;

import com.example.Backend.DTOS.UserAddressDto;
import com.example.Backend.Models.Address;
import com.example.Backend.Models.User;
import com.example.Backend.Repositories.AddressRepository;
import com.example.Backend.Repositories.UserRepository;
import com.example.Backend.Responses.Messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Async
    public CompletableFuture<ResponseEntity<?>> add(Address address,long userId){
        User user = this.userRepository.findById(userId).orElse(null);
        if(user==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No user data")));
        if(this.addressRepository.countByUser_Id(user.getId())==null) {
            address.setStatus("Default");
            String url = "https://nominatim.openstreetmap.org/reverse?lat="+ address.getLat() + "&lon=" +address.getLon()+"&format=json"+"&accept-language=en";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "my-spring-app");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<Map> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    Map.class
            );
            Map body = response.getBody();
            address.setUser(user);
            address.setAddress((String)body.get("display_name"));
            address.setCreateDate(LocalDate.now());
            this.addressRepository.save(address);
            return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
        }
        if(address.getStatus().equals("Default")) this.addressRepository.setStatusToActiveAll(userId);
        String url = "https://nominatim.openstreetmap.org/reverse?lat="+ address.getLat() + "&lon=" +address.getLon()+"&format=json"+"&accept-language=en";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "my-spring-app");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Map.class
        );
        Map body = response.getBody();
        address.setUser(user);
        address.setAddress((String)body.get("display_name"));
        address.setCreateDate(LocalDate.now());
        this.addressRepository.save(address);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> edite(long id,Address address){
        Address existion = this.addressRepository.findById(id).orElse(null);
        if(existion==null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("No data")));
        if(address.getStatus().equals("Default")) this.addressRepository.setStatusToActiveAll(existion.getUser().getId());
        existion.setName(address.getName());
        existion.setLat(address.getLat());
        existion.setLon(address.getLon());
        String url = "https://nominatim.openstreetmap.org/reverse?lat="+ existion.getLat() + "&lon=" +existion.getLon()+"&format=json" + "&accept-language=en";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "my-spring-app");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                Map.class
        );
        Map body = response.getBody();
        existion.setAddress((String) body.get("display_name"));
        existion.setStatus(address.getStatus());
        this.addressRepository.save(existion);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }

    @Async
    public CompletableFuture<ResponseEntity<?>> setDefault(Long id){
        Address address =this.addressRepository.findById(id).orElse(null);
        if(address == null) return CompletableFuture.completedFuture(ResponseEntity.status(404).body(new Message("NO data")));
        this.addressRepository.setStatusToActiveAll(address.getUser().getId());
        address.setStatus("Default");
        this.addressRepository.save(address);
        return CompletableFuture.completedFuture(ResponseEntity.ok(new Message("Successfully")));
    }


    public Address getByUserIdAndStatus(Long id){
        return this.addressRepository.findByUser_IdAndStatus(id,"Default");
    }


    public Address getById(Long id){
        return this.addressRepository.findById(id).orElse(null);
    }


    public List<UserAddressDto> getByUserId(long userId){
        return this.addressRepository.findByUserId(userId);
    }

}
