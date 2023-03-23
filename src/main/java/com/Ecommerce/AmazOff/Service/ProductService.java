package com.Ecommerce.AmazOff.Service;

import com.Ecommerce.AmazOff.DTO.RequestDTO.AddProductRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.ProductResponseDTO;
import com.Ecommerce.AmazOff.Enum.ProductCategory;
import com.Ecommerce.AmazOff.Exception.SellerNotFoundException;
import com.Ecommerce.AmazOff.Model.Product;
import com.Ecommerce.AmazOff.Model.Seller;
import com.Ecommerce.AmazOff.Repository.ProductRepository;
import com.Ecommerce.AmazOff.Repository.SellerRepository;
import com.Ecommerce.AmazOff.convertor.ProductConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;

    public ProductResponseDTO addProduct(AddProductRequestDTO addProductRequestDTO) throws SellerNotFoundException {
        Seller seller;
        //check if seller id is valid
        try{
            seller = sellerRepository.findById(addProductRequestDTO.getSellerId()).get();
        }catch(Exception e){
            throw new SellerNotFoundException("Invalid seller id");
        }

        //create product
        Product product = ProductConvertor.productRequestDTOtoProduct(addProductRequestDTO);
        product.setSeller(seller); //seller is set
        seller.getListOfProducts().add(product);//add product

        sellerRepository.save(seller);

        //make response DTO
        ProductResponseDTO productResponseDTO = ProductConvertor.productToResponseDTO(product);
        return productResponseDTO;
    }

    public List<ProductResponseDTO> getProductsByCategory(ProductCategory productCategory) {

        List<Product> products = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for(Product product:products){
            if(product.getProductCategory().equals(productCategory)){
                productResponseDTOS.add(ProductConvertor.productToResponseDTO(product));
            }
        }
        return productResponseDTOS;
    }
}
