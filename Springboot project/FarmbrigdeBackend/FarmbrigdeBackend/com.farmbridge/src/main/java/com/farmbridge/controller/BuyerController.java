package com.farmbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmbridge.dto.BuyerDTO;
import com.farmbridge.service.BuyerService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/buyers")  
public class BuyerController {
    
    @Autowired
    private BuyerService buyerService;
    
    @GetMapping("/view")  
    public ResponseEntity<?> viewBuyers() {
        return ResponseEntity.ok(buyerService.getAllBuyers());
    }
    
    @PostMapping("/add")  
    public ResponseEntity<?> addBuyer(@RequestBody @Valid BuyerDTO b) {
        System.out.println(b);
        return ResponseEntity.status(HttpStatus.CREATED).body(buyerService.addNewBuyer(b));
    }    
}

