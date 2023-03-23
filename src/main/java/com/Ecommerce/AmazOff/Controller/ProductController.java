package com.Ecommerce.AmazOff.Controller;

import com.Ecommerce.AmazOff.DTO.RequestDTO.AddProductRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.ProductResponseDTO;
import com.Ecommerce.AmazOff.Enum.ProductCategory;
import com.Ecommerce.AmazOff.Exception.SellerNotFoundException;
import com.Ecommerce.AmazOff.Service.ProductService;
import com.Ecommerce.AmazOff.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")

public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("/add")
    public ResponseEntity<ProductResponseDTO> addProduct(@RequestBody AddProductRequestDTO addProductRequestDTO ) throws SellerNotFoundException {
        ProductResponseDTO productResponseDTO;
        try{
            productResponseDTO = productService.addProduct(addProductRequestDTO);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(productResponseDTO,HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/category/{productCategory}")
    public ResponseEntity<List<ProductResponseDTO>> getProductsByCategory(@PathVariable("productCategory") ProductCategory productCategory){
        List<ProductResponseDTO> products = productService.getProductsByCategory(productCategory);
        return new ResponseEntity<>(products,HttpStatus.ACCEPTED);
    }


}
