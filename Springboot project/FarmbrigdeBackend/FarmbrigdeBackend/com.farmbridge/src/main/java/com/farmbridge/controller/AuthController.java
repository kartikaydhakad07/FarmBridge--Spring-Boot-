package com.farmbridge.controller;

import com.farmbridge.service.FarmerServiceImpl;
import com.farmbridge.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final FarmerServiceImpl farmerService;

    public AuthController(FarmerServiceImpl farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String token = farmerService.authenticateFarmer(loginRequest.getContact(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }
}
