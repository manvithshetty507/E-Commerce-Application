package com.Ecommerce.AmazOff.Service;

import com.Ecommerce.AmazOff.DTO.ResponseDTO.ItemResponseDTO;
import com.Ecommerce.AmazOff.Exception.ProductNotFoundException;
import com.Ecommerce.AmazOff.Model.Item;
import com.Ecommerce.AmazOff.Model.Product;
import com.Ecommerce.AmazOff.Repository.ProductRepository;
import com.Ecommerce.AmazOff.convertor.ItemConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ProductRepository productRepository;
    public ItemResponseDTO viewItem(int productId) throws ProductNotFoundException {

        Product product;
        try{
            product = productRepository.findById(productId).get();
        }catch(Exception e){
            throw new ProductNotFoundException("Invalid product id");
        }

        Item item = Item.builder()
                .requiredQuantity(0)
                .product(product) //map product to item
                .build();

        product.setItem(item); //map item to product

        productRepository.save(product); //save the parent to register change

        //prepare the response DTO

        ItemResponseDTO itemResponseDTO = ItemConvertor.itemToTeamResponseDTO(item);
        return itemResponseDTO;
    }
}
