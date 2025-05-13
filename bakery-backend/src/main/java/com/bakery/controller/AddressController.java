package com.bakery.controller;

import com.bakery.dto.AddressDTO;
import com.bakery.model.Address;
import com.bakery.model.User;
import com.bakery.repository.AddressRepository;
import com.bakery.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @PostMapping("/{userId}")
    public ResponseEntity<Address> addAddress(
            @PathVariable("userId") Long userId,
            @RequestBody AddressDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Address address = new Address();
        address.setName(dto.getName());
        address.setPhoneNumber(dto.getPhoneNumber());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());
        address.setUser(user);

        addressRepository.save(address);
        return ResponseEntity.ok(address);
    }

 
    @GetMapping("/{userId}")
    public ResponseEntity<List<Address>> getAddresses(@PathVariable("userId") Long userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return ResponseEntity.ok(addresses);
    }

   
    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            return ResponseEntity.badRequest().body("Address not found");
        }
        addressRepository.deleteById(addressId);
        return ResponseEntity.ok("Address deleted successfully");
    }
}
