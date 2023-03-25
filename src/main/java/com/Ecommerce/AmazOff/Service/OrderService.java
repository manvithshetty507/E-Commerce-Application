package com.Ecommerce.AmazOff.Service;

import com.Ecommerce.AmazOff.DTO.RequestDTO.OrderRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.OrderResponseDTO;
import com.Ecommerce.AmazOff.Enum.ProductStatus;
import com.Ecommerce.AmazOff.Exception.CustomerNotFoundException;
import com.Ecommerce.AmazOff.Exception.InsufficientQuantity;
import com.Ecommerce.AmazOff.Exception.ProductNotFoundException;
import com.Ecommerce.AmazOff.Model.*;
import com.Ecommerce.AmazOff.Repository.CustomerRepository;
import com.Ecommerce.AmazOff.Repository.ItemRepository;
import com.Ecommerce.AmazOff.Repository.OrderedRepository;
import com.Ecommerce.AmazOff.Repository.ProductRepository;
import com.Ecommerce.AmazOff.convertor.OrderedConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderedRepository orderedRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;


    public OrderResponseDTO placeOrder(OrderRequestDTO orderRequestDTO) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantity {
        //check if customer id is valid
        Customer customer;

        try{
            customer = customerRepository.findById(orderRequestDTO.getCustomerId()).get();
        }catch(Exception e){
            throw new CustomerNotFoundException("Customer Id not Valid!!");
        }
        //check if product id is valid
        Product product;

        try{
            product = productRepository.findById(orderRequestDTO.getProductId()).get();
        }catch(Exception e){
            throw new ProductNotFoundException("Invalid product Id!!");
        }

        if(orderRequestDTO.getRequiredQuantity() > product.getQuantity()){
            throw new InsufficientQuantity("Sorry insufficient product available !!");
        }

        //make order object
        Ordered order = new Ordered();
        int total = 0;
        total += product.getPrice()*orderRequestDTO.getRequiredQuantity();
        int delivery = 0;
        if(total < 500){
            delivery = 50;
        }
        order.setTotal(total);
        order.setDeliveryCharge(delivery);
        //prepare card to  make payment
        Card card = customer.getListOfCards().get(0);
        StringBuilder cardNo = new StringBuilder();

        for(int i=0;i<15;i++){
            cardNo.append("X");
        }
        //append last for
        cardNo.append(card.getCardNo().substring(15));

        Item item = new Item();
        item.setRequiredQuantity(orderRequestDTO.getRequiredQuantity());
        item.setProduct(product);
        item.setOrders(order);
        order.getOrderedItems().add(item);
        order.setCustomer(customer);

        int leftQuantity = product.getQuantity()-orderRequestDTO.getRequiredQuantity();
        if(leftQuantity<=0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        product.setQuantity(leftQuantity);

        customer.getListOfOrders().add(order);
        Customer savedCustomer = customerRepository.save(customer);
        Ordered savedOrder = savedCustomer.getListOfOrders().get(savedCustomer.getListOfOrders().size()-1);

        //prepare response DTO
        OrderResponseDTO orderResponseDTO = OrderResponseDTO.builder()
                .productName(product.getName())
                .orderDate(savedOrder.getOrderDate())
                .quantityOrdered(orderRequestDTO.getRequiredQuantity())
                .cardUsedForPayment(String.valueOf(cardNo))
                .itemPrice(product.getPrice())
                .totalCost(order.getTotal())
                .deliveryCharge(40)
                .build();

        return orderResponseDTO;
    }

    public List<Item> getAllItems(int orderId) {
        Ordered ordered = orderedRepository.findById(orderId).get();
        return ordered.getOrderedItems();
    }
}
