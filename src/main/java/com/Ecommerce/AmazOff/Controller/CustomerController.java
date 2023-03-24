package com.Ecommerce.AmazOff.Controller;

import com.Ecommerce.AmazOff.DTO.RequestDTO.CustomerRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.CustomerResponseDTO;
import com.Ecommerce.AmazOff.Exception.CustomerNotFoundException;
import com.Ecommerce.AmazOff.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getById")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(@RequestParam int customerId) throws CustomerNotFoundException {
        CustomerResponseDTO customerResponseDTO;
        try{
            customerResponseDTO = customerService.getCustomerById(customerId);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customerResponseDTO,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerResponseDTO>> getAllCustomers(){
        List<CustomerResponseDTO> customerResponseDTOS = customerService.getAllCustomers();
        return new ResponseEntity<>(customerResponseDTOS,HttpStatus.ACCEPTED);
    }

    @GetMapping("/getByEmail")
    public ResponseEntity<CustomerResponseDTO> getCustomerByEmail(@RequestParam String email) throws CustomerNotFoundException {
        CustomerResponseDTO customerResponseDTO;
        try{
            customerResponseDTO = customerService.getCustomerByEmail(email);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customerResponseDTO,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("deleteById")
    public String deleteById(@RequestParam int id){
        customerService.deleteById(id);
        return "Success";
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CustomerResponseDTO> updateACustomerById(@PathVariable int id,@RequestBody CustomerRequestDTO customerRequestDTO){
        CustomerResponseDTO customerResponseDTO;

        try{
            customerResponseDTO = customerService.updateCustomerById(id,customerRequestDTO);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(customerResponseDTO,HttpStatus.ACCEPTED);
    }

}
