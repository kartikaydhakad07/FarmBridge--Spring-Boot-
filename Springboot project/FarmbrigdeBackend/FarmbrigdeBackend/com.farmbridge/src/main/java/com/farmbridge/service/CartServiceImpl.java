package com.farmbridge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.farmbridge.dto.CartDTO;
import com.farmbridge.dto.CartItemDTO;
import com.farmbridge.entities.Cart;
import com.farmbridge.entities.CartItem;
import com.farmbridge.entities.Crop;
import com.farmbridge.entities.Farmer;
import com.farmbridge.repository.ApiResponse;
import com.farmbridge.repository.CartItemRepository;
import com.farmbridge.repository.CartRepository;
import com.farmbridge.repository.CropRepository;


@Service
@Transactional
public class CartServiceImpl implements CartService {

		@Autowired
		private CartRepository cartRepo;
		
		@Autowired
		private CartItemRepository cartItemRepo;
		
		@Autowired
		private CropRepository cropRepo;
		
		@Autowired
		private ModelMapper mapper;
	
	@Override
	public ApiResponse add(CartItemDTO cartItem,CartDTO c) {
		
		System.out.println(c);
		CartItem cItem=mapper.map(cartItem, CartItem.class);
		
		Crop crop=cropRepo.findById(cartItem.getCrop_id()).orElse(null);
		Cart cart=mapper.map(c, Cart.class);
		
		System.out.println(cItem.getId());
		if(cartRepo.existsById(cart.getId()))
		
		
		if(cart.getCartItem()==null)
		{
			cart.setCartItem(new ArrayList<>());
		}
		
		else {
			cart.getCartItem().add(cItem);
			
		}
		cItem.setCart(cart);
		cItem.setCrop(crop);
		cartItemRepo.save(cItem);
		return new ApiResponse("cartItem added"+cItem.getId() );
	}
	
	@Override
	public List<CartItemDTO> displayCart(long id) {
		Cart cart=cartRepo.findById(id).orElse(null); 
		
		if(cart!=null)
		{
			List<CartItem> cartItem=cart.getCartItem();
			
			return cartItem.stream().map(carti->{ CartItemDTO c=mapper.map(carti, CartItemDTO.class);
			                                                   c.setCart_id(carti.getCart().getId());
			                                                   c.setCrop_id(carti.getCrop().getId());
			                                                   return c;
			                                                   }).collect(Collectors.toList());	
		}
		return null;
		
	}

	@Override
	public ApiResponse deleteCartItem(long id) {
		CartItem item = cartItemRepo.findById(id).orElseThrow(() -> 
        new IllegalArgumentException("Cart item  with ID " + id + " not found.")
    );
		cartItemRepo.delete(item);
		return new ApiResponse("Cart item deleted"+id);
	}

	@Override
	public CartDTO findById(long id) {
		System.out.println(id);
		  Cart c= cartRepo.findById(id).orElse(null);
		  return mapper.map(c, CartDTO.class);
		}
	

}
