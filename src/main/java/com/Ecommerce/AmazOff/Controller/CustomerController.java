package com.Ecommerce.AmazOff.Controller;

import com.Ecommerce.AmazOff.DTO.RequestDTO.CustomerRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.CustomerResponseDTO;
import com.Ecommerce.AmazOff.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CustomerResponseDTO> addCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){

        CustomerResponseDTO customerResponseDTO = customerService.addCustomer(customerRequestDTO);
        return new ResponseEntity<>(customerResponseDTO, HttpStatus.ACCEPTED);
    }


}
