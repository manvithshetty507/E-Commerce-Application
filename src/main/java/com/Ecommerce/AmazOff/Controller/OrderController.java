package com.Ecommerce.AmazOff.Controller;

import com.Ecommerce.AmazOff.DTO.RequestDTO.OrderRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.OrderResponseDTO;
import com.Ecommerce.AmazOff.Model.Item;
import com.Ecommerce.AmazOff.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<OrderResponseDTO> placeOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        OrderResponseDTO orderResponseDTO;

        try{
            orderResponseDTO = orderService.placeOrder(orderRequestDTO);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(orderResponseDTO,HttpStatus.CREATED);
    }

    @GetMapping("/getItems")
    public ResponseEntity<List<Item>> getAllItems(@RequestParam int orderId){
        List<Item> items = orderService.getAllItems(orderId);
        return new ResponseEntity<>(items,HttpStatus.ACCEPTED);
    }
}
