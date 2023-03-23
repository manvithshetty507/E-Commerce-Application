package com.Ecommerce.AmazOff.Service;

import com.Ecommerce.AmazOff.DTO.RequestDTO.CustomerRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.CustomerResponseDTO;
import com.Ecommerce.AmazOff.Model.Cart;
import com.Ecommerce.AmazOff.Model.Customer;
import com.Ecommerce.AmazOff.Repository.CustomerRepository;
import com.Ecommerce.AmazOff.convertor.CustomerConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO) {

        Customer customer = CustomerConvertor.customerRequestDTOtoCustomer(customerRequestDTO);

        //allocate cart to customer
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        //set cart to customer
        customer.setCart(cart);
        customerRepository.save(customer);

        CustomerResponseDTO customerResponseDTO = CustomerConvertor.customerToCustomerResponseDTO(customer);
        return customerResponseDTO;
    }
}
