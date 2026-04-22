package com.example.Backend.Controllers;

import com.example.Backend.Models.Address;
import com.example.Backend.Requests.AddressRequest;
import com.example.Backend.Requests.UserAddressUpdateRequest;
import com.example.Backend.Responses.Messages.Message;
import com.example.Backend.Services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin
@RequestMapping("user/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public CompletableFuture<ResponseEntity<?>> add(@ModelAttribute AddressRequest address){
        try{
            return this.addressService.add(
                    new Address(
                           address.getName(),
                            address.getStatus(),
                            address.getLat(),
                            address.getLon(),
                            LocalDate.now()
                    ),
                    address.getUserId()
            );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/default")
    public CompletableFuture<ResponseEntity<?>> setDefault(@RequestParam Long addressId){
        try{
            return this.addressService.setDefault(addressId);
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @PutMapping("/edite")
    public CompletableFuture<ResponseEntity<?>> update(@ModelAttribute UserAddressUpdateRequest address){
        try{
             return this.addressService.edite(
                     address.getAddressId(),
                     new Address(address.getName(),address.getStatus(),address.getLat(),address.getLon())
             );
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.status(500).body(new Message(e.getMessage())));
        }
    }

    @GetMapping("/get/all/by/user")
    public ResponseEntity<?> getAllByUserId(@RequestParam Long userId){
        try{
            return ResponseEntity.ok(this.addressService.getByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Message(e.getMessage()));
        }
    }
}
