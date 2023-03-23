package com.Ecommerce.AmazOff.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerResponseDTO {

    private String name;

    private String email;

    private String mobNo;
}
