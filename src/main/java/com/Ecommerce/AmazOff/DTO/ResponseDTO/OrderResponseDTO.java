package com.Ecommerce.AmazOff.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class OrderResponseDTO {

    private String productName;
    private Date orderDate;
    private int itemPrice;
    private int quantityOrdered;
    private int deliveryCharge;
    private int totalCost;
    private String cardUsedForPayment;
}
