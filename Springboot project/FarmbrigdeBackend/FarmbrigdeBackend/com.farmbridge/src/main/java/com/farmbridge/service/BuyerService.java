package com.farmbridge.service;

import java.util.List;
import com.farmbridge.dto.BuyerDTO;
import com.farmbridge.dto.CartDTO;

public interface BuyerService {
    List<BuyerDTO> getAllBuyers();
    BuyerDTO addNewBuyer(BuyerDTO buyerDTO);
    public BuyerDTO findById(Long id);
	
}

