package com.Ecommerce.AmazOff.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class AddSellerRequestDTO {
    private String name;

    private String mobNo;

    private String email;

    private String panNo;

}
