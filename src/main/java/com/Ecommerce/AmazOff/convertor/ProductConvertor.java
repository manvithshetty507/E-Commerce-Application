package com.Ecommerce.AmazOff.convertor;

import com.Ecommerce.AmazOff.DTO.RequestDTO.AddProductRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.ProductResponseDTO;
import com.Ecommerce.AmazOff.Enum.ProductStatus;
import com.Ecommerce.AmazOff.Model.Product;

public class ProductConvertor {

    public static Product productRequestDTOtoProduct(AddProductRequestDTO addProductRequestDTO){
        return Product.builder()
                .name(addProductRequestDTO.getProductName())
                .price(addProductRequestDTO.getPrice())
                .quantity(addProductRequestDTO.getQuantity())
                .productCategory(addProductRequestDTO.getProductCategory())
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
