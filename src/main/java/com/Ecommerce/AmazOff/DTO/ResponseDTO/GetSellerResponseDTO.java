package com.Ecommerce.AmazOff.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetSellerResponseDTO {
    private String name;
    private String panNo;
}
