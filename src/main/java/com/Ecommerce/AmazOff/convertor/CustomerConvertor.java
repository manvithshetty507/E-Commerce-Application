package com.Ecommerce.AmazOff.convertor;

import com.Ecommerce.AmazOff.DTO.RequestDTO.CustomerRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.CustomerResponseDTO;
import com.Ecommerce.AmazOff.Model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerConvertor {

    public static Customer customerRequestDTOtoCustomer(CustomerRequestDTO customerRequestDTO){

        return Customer.builder()
                .name(customerRequestDTO.getName())
                .age(customerRequestDTO.getAge())
                .email(customerRequestDTO.getEmail())
                .mobNo(customerRequestDTO.getMobNo())
                .build();
    }
    public static CustomerResponseDTO customerToCustomerResponseDTO(Customer customer){

        return CustomerResponseDTO.builder()
                .email(customer.getEmail())
                .name(customer.getName())
                .mobNo(customer.getMobNo())
                .build();
    }
}
