package com.Ecommerce.AmazOff.convertor;

import com.Ecommerce.AmazOff.DTO.RequestDTO.SellerRequestDTO;
import com.Ecommerce.AmazOff.Model.Seller;

public class SellerConvertor {

    public static Seller SellerRequestDTOtoSeller(SellerRequestDTO sellerRequestDTO){

        return  Seller.builder().
                name(sellerRequestDTO.getName()).
                email(sellerRequestDTO.getEmail()).
                mobNo(sellerRequestDTO.getMobNo()).
                panNo(sellerRequestDTO.getPanNo()).
                build();
    }
}
