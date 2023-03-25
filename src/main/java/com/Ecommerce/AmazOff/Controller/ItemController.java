package com.Ecommerce.AmazOff.Controller;

import com.Ecommerce.AmazOff.DTO.ResponseDTO.ItemResponseDTO;
import com.Ecommerce.AmazOff.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/view/{productId}")
    public ResponseEntity<ItemResponseDTO> viewItem(@PathVariable int productId){

        ItemResponseDTO itemResponseDTO;
        try{
            itemResponseDTO = itemService.viewItem(productId);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(itemResponseDTO, HttpStatus.ACCEPTED);
    }
}
