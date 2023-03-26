package com.Ecommerce.AmazOff.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartResponseDTO {

    private String productName;
    private int price;
    private int requiredQuantity;

}
