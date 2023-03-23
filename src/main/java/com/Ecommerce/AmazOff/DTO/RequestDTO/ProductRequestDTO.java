package com.Ecommerce.AmazOff.DTO.RequestDTO;

import com.Ecommerce.AmazOff.Enum.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder

public class ProductRequestDTO {
    private int sellerId;

    private String productName;

    private int price;

    private int quantity;

    private ProductCategory productCategory;
}
