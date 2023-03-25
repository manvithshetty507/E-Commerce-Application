package com.Ecommerce.AmazOff.DTO.ResponseDTO;

import com.Ecommerce.AmazOff.Enum.ProductCategory;
import com.Ecommerce.AmazOff.Enum.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ItemResponseDTO {

    private String productName;
    private int price;
    private ProductCategory productCategory;
    private ProductStatus productStatus;
}
