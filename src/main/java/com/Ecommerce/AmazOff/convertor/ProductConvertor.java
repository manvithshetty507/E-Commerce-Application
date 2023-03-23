package com.Ecommerce.AmazOff.convertor;

import com.Ecommerce.AmazOff.DTO.RequestDTO.ProductRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.ProductResponseDTO;
import com.Ecommerce.AmazOff.Enum.ProductStatus;
import com.Ecommerce.AmazOff.Model.Product;

public class ProductConvertor {

    public static Product productRequestDTOtoProduct(ProductRequestDTO productRequestDTO){
        return Product.builder()
                .name(productRequestDTO.getProductName())
                .price(productRequestDTO.getPrice())
                .quantity(productRequestDTO.getQuantity())
                .productCategory(productRequestDTO.getProductCategory())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDTO productToResponseDTO(Product product){

        return ProductResponseDTO.builder()
                .productStatus(product.getProductStatus())
                .price(product.getPrice())
                .name(product.getName())
                .quantity(product.getQuantity()).build();
    }
}
