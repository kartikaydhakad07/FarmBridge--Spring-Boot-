package com.farmbridge.service;

import com.farmbridge.entities.Farmer;
import com.farmbridge.repository.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private FarmerRepository farmerRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Assuming 'username' is the contact number
        Farmer farmer = farmerRepo.findByContact(username)
                .orElseThrow(() -> new UsernameNotFoundException("Farmer not found with contact: " + username));

        return User.builder()
                .username(farmer.getContact())
                .password(farmer.getPassword())
                .roles(farmer.getRole())  // Assuming role is stored in the 'role' field
                .build();
    }
}
