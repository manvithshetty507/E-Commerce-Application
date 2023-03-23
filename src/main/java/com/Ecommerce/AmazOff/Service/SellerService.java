package com.Ecommerce.AmazOff.Service;

import com.Ecommerce.AmazOff.DTO.RequestDTO.AddSellerRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.GetSellerResponseDTO;
import com.Ecommerce.AmazOff.Exception.SellerNotFoundException;
import com.Ecommerce.AmazOff.Model.Seller;
import com.Ecommerce.AmazOff.Repository.SellerRepository;
import com.Ecommerce.AmazOff.convertor.SellerConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;

    public void addSeller(AddSellerRequestDTO addSellerRequestDTO){
        Seller seller = SellerConvertor.SellerRequestDTOtoSeller(addSellerRequestDTO);
        sellerRepository.save(seller);
    }

    public ResponseEntity<List<GetSellerResponseDTO>> GetAllSellers() {
        List<GetSellerResponseDTO> allSellers = new ArrayList<>();
        List<Seller> sellers = sellerRepository.findAll();
        for(Seller s : sellers){
            GetSellerResponseDTO g = new GetSellerResponseDTO(s.getName(),s.getPanNo());
            allSellers.add(g);
        }
        return new ResponseEntity<>(allSellers, HttpStatus.CREATED);
    }


    public GetSellerResponseDTO getSellerByPan(String panNo) throws SellerNotFoundException{

        Seller seller;

        seller = sellerRepository.findByPanNo(panNo);

        if(seller == null){
            throw new SellerNotFoundException("Invalid Pan Number");
        }

        GetSellerResponseDTO getSellerResponseDTO = new GetSellerResponseDTO(seller.getName(), seller.getPanNo());
        return getSellerResponseDTO;

    }


}
