package com.Ecommerce.AmazOff.Controller;

import com.Ecommerce.AmazOff.DTO.RequestDTO.OrderRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.CartResponseDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.OrderResponseDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.ProductResponseDTO;
import com.Ecommerce.AmazOff.Exception.CustomerNotFoundException;
import com.Ecommerce.AmazOff.Exception.InsufficientQuantity;
import com.Ecommerce.AmazOff.Exception.ProductNotFoundException;
import com.Ecommerce.AmazOff.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody OrderRequestDTO orderRequestDTO) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantity {

        try{
            cartService.addToCart(orderRequestDTO);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage()+": Failed!!",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Successfully added to cart", HttpStatus.ACCEPTED);
    }

    @PostMapping("/checkOut/{customerId}")
    public ResponseEntity<List<OrderResponseDTO>> checkout(@PathVariable int customerId){
        List<OrderResponseDTO> orderResponseDTOs;
        try{
            orderResponseDTOs = cartService.checkOut(customerId);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(orderResponseDTOs,HttpStatus.ACCEPTED);
    }

    @GetMapping("/view/{customerId}")
    public ResponseEntity<List<CartResponseDTO>> viewCart(@PathVariable int customerId){

        List<CartResponseDTO> cartResponseDTOS = new ArrayList<>();
        try{
            cartResponseDTOS = cartService.viewCart(customerId);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartResponseDTOS,HttpStatus.ACCEPTED);
    }
}
