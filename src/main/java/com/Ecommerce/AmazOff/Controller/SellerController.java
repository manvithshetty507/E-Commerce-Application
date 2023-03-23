package com.Ecommerce.AmazOff.Controller;

import com.Ecommerce.AmazOff.DTO.RequestDTO.AddSellerRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.GetSellerResponseDTO;
import com.Ecommerce.AmazOff.Exception.SellerNotFoundException;
import com.Ecommerce.AmazOff.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity<String> addSeller(@RequestBody AddSellerRequestDTO addSellerRequestDTO){
        sellerService.addSeller(addSellerRequestDTO);
        return new ResponseEntity<>("Congrats you are now a seller", HttpStatus.CREATED);
    }

    @GetMapping("/getAllSellers")
    public ResponseEntity<List<GetSellerResponseDTO>> getAllSeller(){
        return sellerService.GetAllSellers();
    }

    @GetMapping("/sellerByPan")
    public ResponseEntity<GetSellerResponseDTO> getSellerByPan(@RequestParam String panNo){

        GetSellerResponseDTO sellerByPan;

        try{
            sellerByPan = sellerService.getSellerByPan(panNo);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sellerByPan,HttpStatus.ACCEPTED);

    }



}
