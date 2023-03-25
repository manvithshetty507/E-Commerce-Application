package com.Ecommerce.AmazOff.convertor;

import com.Ecommerce.AmazOff.DTO.RequestDTO.OrderRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.OrderResponseDTO;
import com.Ecommerce.AmazOff.Model.Ordered;
import com.Ecommerce.AmazOff.Repository.ProductRepository;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;

@UtilityClass
public class OrderedConvertor {
    @Autowired
    ProductRepository productRepository;
    public static Ordered orderRequestDTOtoOrdered(OrderRequestDTO orderRequestDTO){

        return Ordered.builder()
                .quantityOrdered(orderRequestDTO.getRequiredQuantity())
                .build();
    }

    public static OrderResponseDTO orderToOrderResponseDTO(Ordered ordered){

        return OrderResponseDTO.builder()
                .orderDate(ordered.getOrderDate())
                .quantityOrdered(ordered.getQuantityOrdered())
                .deliveryCharge(ordered.getDeliveryCharge())
                .totalCost(ordered.getTotal()+ordered.getDeliveryCharge())
                .build();
    }
}
