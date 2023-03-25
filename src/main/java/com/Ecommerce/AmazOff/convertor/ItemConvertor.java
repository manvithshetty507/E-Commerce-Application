package com.Ecommerce.AmazOff.convertor;

import com.Ecommerce.AmazOff.DTO.ResponseDTO.ItemResponseDTO;
import com.Ecommerce.AmazOff.Model.Item;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemConvertor {

    public static ItemResponseDTO itemToTeamResponseDTO(Item item){

        return ItemResponseDTO.builder()
                .productName(item.getProduct().getName())
                .price(item.getProduct().getPrice())
                .productCategory(item.getProduct().getProductCategory())
                .productStatus(item.getProduct().getProductStatus())
                .build();

    }

}
