package com.Ecommerce.AmazOff.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderRequestDTO {

    private int productId;
    private int customerId;
    private int requiredQuantity;
}
