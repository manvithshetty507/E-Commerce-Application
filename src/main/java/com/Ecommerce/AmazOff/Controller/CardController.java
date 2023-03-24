package com.Ecommerce.AmazOff.Controller;

import com.Ecommerce.AmazOff.DTO.RequestDTO.CardRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.CardResponseDTO;
import com.Ecommerce.AmazOff.Exception.CustomerNotFoundException;
import com.Ecommerce.AmazOff.Model.Customer;
import com.Ecommerce.AmazOff.Repository.CustomerRepository;
import com.Ecommerce.AmazOff.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<CardResponseDTO> addCard(@RequestBody CardRequestDTO cardRequestDTO) throws CustomerNotFoundException {

        CardResponseDTO cardResponseDTO;
        try{
            cardResponseDTO = customerService.addCard(cardRequestDTO);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(cardResponseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCardById(@RequestParam int customerId,int cardId) throws Exception {

        try{
            customerService.deleteCardById(customerId,cardId);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Success",HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<String> deleteCards(@RequestParam int customerId){
        try{
            customerService.deleteCards(customerId);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success",HttpStatus.ACCEPTED);
    }
}
