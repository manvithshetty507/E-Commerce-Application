package com.Ecommerce.AmazOff.convertor;

import com.Ecommerce.AmazOff.DTO.RequestDTO.AddSellerRequestDTO;
import com.Ecommerce.AmazOff.Model.Seller;

public class SellerConvertor {

    public static Seller SellerRequestDTOtoSeller(AddSellerRequestDTO addSellerRequestDTO){

        return  Seller.builder().
                name(addSellerRequestDTO.getName()).
                email(addSellerRequestDTO.getEmail()).
                mobNo(addSellerRequestDTO.getMobNo()).
                panNo(addSellerRequestDTO.getPanNo()).
                build();
    }
}
