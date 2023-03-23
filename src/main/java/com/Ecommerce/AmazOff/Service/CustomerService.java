package com.Ecommerce.AmazOff.Service;

import com.Ecommerce.AmazOff.DTO.RequestDTO.CustomerRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.CustomerResponseDTO;
import com.Ecommerce.AmazOff.Model.Customer;
import com.Ecommerce.AmazOff.convertor.CustomerConvertor;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO) {

        Customer customer = CustomerConvertor.customerRequestDTOtoCustomer(customerRequestDTO);

        CustomerResponseDTO customerResponseDTO = CustomerConvertor.customerToCustomerResponseDTO(customer);
        return customerResponseDTO;
    }
}
