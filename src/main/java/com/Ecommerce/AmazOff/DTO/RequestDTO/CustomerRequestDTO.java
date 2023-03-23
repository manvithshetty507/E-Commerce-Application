package com.Ecommerce.AmazOff.DTO.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class CustomerRequestDTO {

    private int age;

    private String name;

    private String email;

    private String mobNo;
}
