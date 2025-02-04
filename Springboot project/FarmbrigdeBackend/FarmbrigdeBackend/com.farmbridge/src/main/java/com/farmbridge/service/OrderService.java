package com.farmbridge.service;

import com.farmbridge.dto.CartDTO;
import com.farmbridge.dto.CartItemDTO;
import com.farmbridge.dto.OrderDTO;
import com.farmbridge.dto.OrderDetailsDTO;


public interface OrderService {

	public OrderDTO createOrderFromCart(CartDTO cart , long id);
	
	
	
	
}
