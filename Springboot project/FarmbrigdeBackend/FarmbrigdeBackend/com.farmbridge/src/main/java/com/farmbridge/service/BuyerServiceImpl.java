package com.farmbridge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbridge.dto.BuyerDTO;
import com.farmbridge.entities.Buyer;
import com.farmbridge.entities.Cart;
import com.farmbridge.repository.BuyerRepository;
import com.farmbridge.repository.CartRepository;

@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;
    
    @Autowired 
    private BuyerRepository buyerRepo;
    
    @Autowired 
    private CartRepository cartRepo;
    
    @Autowired
    private ModelMapper mapper;
    
    

    @Override
    public List<BuyerDTO> getAllBuyers() {
        return buyerRepository.findAll().stream()
                .map(buyer->{BuyerDTO b=mapper.map(buyer,BuyerDTO.class);
//                                       b.setCart_id(buyer.getCart().getId());
                                       return b;})
                .collect(Collectors.toList());
    }

    @Override
    public BuyerDTO addNewBuyer(BuyerDTO buyerDTO) {
        Cart c=new Cart();
        
        
    	Buyer buyer = convertToEntity(buyerDTO);
        Buyer savedBuyer = buyerRepository.save(buyer);
        c.setId(savedBuyer.getId());
        c.setBuyer(savedBuyer);
        cartRepo.save(c);
       System.out.println(c);
        return mapper.map(savedBuyer, BuyerDTO.class);
    }

    private BuyerDTO convertToDTO(Buyer buyer) {
        BuyerDTO dto = new BuyerDTO();
        dto.setId(buyer.getId());
        dto.setFullName(buyer.getFullName());
        dto.setContactNo(buyer.getContactNo());
        dto.setEmail(buyer.getEmail());
        dto.setAddress(buyer.getAddress());
        dto.setPincode(buyer.getPincode());
        
        return dto;
    }

    @Override
	public BuyerDTO findById(Long id) {
		  Buyer c= buyerRepo.findById(id).orElse(null);
		 BuyerDTO buyer= mapper.map(c, BuyerDTO.class);
		
		 buyer.setCart_id(c.getCart().getId());
		  return buyer;
		}

	private Buyer convertToEntity(BuyerDTO buyerDTO) {
        Buyer buyer = new Buyer();
        buyer.setFullName(buyerDTO.getFullName());
        buyer.setContactNo(buyerDTO.getContactNo());
        buyer.setEmail(buyerDTO.getEmail());
        buyer.setAddress(buyerDTO.getAddress());
        buyer.setPincode(buyerDTO.getPincode());
        // Note: You might want to set a default password or handle it differently
        buyer.setPassword("defaultPassword");
        
        return buyer;
    }
}

