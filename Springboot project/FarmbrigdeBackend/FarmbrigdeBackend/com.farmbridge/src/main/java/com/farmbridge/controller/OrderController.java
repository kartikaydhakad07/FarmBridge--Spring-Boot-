package com.farmbridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmbridge.dto.CartDTO;
import com.farmbridge.dto.CartItemDTO;
import com.farmbridge.dto.OrderDTO;
import com.farmbridge.dto.OrderDetailsDTO;
import com.farmbridge.service.CartService;
import com.farmbridge.service.OrderService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orders")
public class OrderController {

	
	
	@Autowired
	OrderService orderService;
	
	
	@Autowired
	CartService cartService;
	
//	
//	@GetMapping("/view")
//	public ResponseEntity<?> getAllOrders(){
//		
//		return ResponseEntity.ok(orderService.getAllOrders());
//	}
	
	
	@PostMapping("/add/{cartId}/{buyerId}")
	public ResponseEntity<?> addOrder(@PathVariable long cartId,@PathVariable long buyerId){
		
		CartDTO cart = cartService.findById(cartId);
		if (cart == null) {
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart not found with ID: " + cartId);
		}

		
		OrderDTO order=orderService.createOrderFromCart(cart,buyerId);
		
		
		
	
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
		
	}
	
}
