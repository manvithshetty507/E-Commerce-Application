package com.Ecommerce.AmazOff.Controller;

import com.Ecommerce.AmazOff.DTO.RequestDTO.SellerRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.SellerResponseDTO;
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
    public ResponseEntity<String> addSeller(@RequestBody SellerRequestDTO sellerRequestDTO){
        sellerService.addSeller(sellerRequestDTO);
        return new ResponseEntity<>("Congrats you are now a seller", HttpStatus.CREATED);
    }

    @GetMapping("/getAllSellers")
    public ResponseEntity<List<SellerResponseDTO>> getAllSeller(){
        return sellerService.GetAllSellers();
    }

    @GetMapping("/sellerByPan")
    public ResponseEntity<SellerResponseDTO> getSellerByPan(@RequestParam String panNo){

        SellerResponseDTO sellerByPan;

        try{
            sellerByPan = sellerService.getSellerByPan(panNo);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(sellerByPan,HttpStatus.ACCEPTED);

    }



}
