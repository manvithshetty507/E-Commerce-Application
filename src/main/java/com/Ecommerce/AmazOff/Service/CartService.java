package com.Ecommerce.AmazOff.Service;

import com.Ecommerce.AmazOff.DTO.RequestDTO.OrderRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.CartResponseDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.CustomerResponseDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.OrderResponseDTO;
import com.Ecommerce.AmazOff.Enum.ProductStatus;
import com.Ecommerce.AmazOff.Exception.CheckOutFailedException;
import com.Ecommerce.AmazOff.Exception.CustomerNotFoundException;
import com.Ecommerce.AmazOff.Exception.InsufficientQuantity;
import com.Ecommerce.AmazOff.Exception.ProductNotFoundException;
import com.Ecommerce.AmazOff.Model.*;
import com.Ecommerce.AmazOff.Repository.CartRepository;
import com.Ecommerce.AmazOff.Repository.CustomerRepository;
import com.Ecommerce.AmazOff.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    JavaMailSender emailSender;
    public void addToCart(OrderRequestDTO orderRequestDTO) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantity {

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

        Cart cart = customer.getCart();
        int cost = cart.getCartTotal();
        cart.setCartTotal(cost+orderRequestDTO.getRequiredQuantity()*product.getPrice());

        //Add item to current cart

        Item item = Item.builder()
                .requiredQuantity(orderRequestDTO.getRequiredQuantity())
                .cart(cart)
                .product(product)
                .build();
        cart.getListOfItems().add(item);

        customerRepository.save(customer);
    }

    public List<OrderResponseDTO> checkOut(int customerId) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantity, CheckOutFailedException {

        //check if customer id is valid
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        List<OrderResponseDTO> orderResponseDtos = new ArrayList<>();

        int totalCost = customer.getCart().getCartTotal();
        Cart cart = customer.getCart();
        int cartTotal = cart.getCartTotal();

        int delivery = 0;
        if(cart.getCartTotal() < 500){
            delivery = 40;
        }

        for(Item item: cart.getListOfItems()){
            Ordered order = new Ordered();
            order.setTotal(item.getRequiredQuantity()*item.getProduct().getPrice());
            order.setDeliveryCharge(delivery);
            order.setCustomer(customer);
            order.getOrderedItems().add(item);

            //prepare card to  make payment
            Card card = customer.getListOfCards().get(0);
            StringBuilder cardNo = new StringBuilder();

            for(int i=0;i<15;i++){
                cardNo.append("X");
            }
            //append last for
            cardNo.append(card.getCardNo().substring(15));
            order.setCardUsedForPayment(String.valueOf(cardNo));

            int leftQuantity = item.getProduct().getQuantity()-item.getRequiredQuantity();

            if(leftQuantity<=0)
                item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
            item.getProduct().setQuantity(leftQuantity);

            customer.getListOfOrders().add(order);

            // prepare response dto
            OrderResponseDTO orderResponseDto = OrderResponseDTO.builder()
                    .productName(item.getProduct().getName())
                    .orderDate(order.getOrderDate())
                    .quantityOrdered(item.getRequiredQuantity())
                    .cardUsedForPayment(String.valueOf(cardNo))
                    .itemPrice(item.getProduct().getPrice())
                    .totalCost(order.getTotal())
                    .deliveryCharge(delivery)
                    .build();

            orderResponseDtos.add(orderResponseDto);
            item.setCart(null);

        }
        // send an email

        String text = "Congrats your order with total value "+cartTotal+" has been placed";

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mt1138@gmail.com");
        message.setTo(customer.getEmail());
        message.setSubject("Order Placed Notification");
        message.setText(text);
        emailSender.send(message);


        cart.setListOfItems(new ArrayList<>());
        cart.setCartTotal(0);
        customerRepository.save(customer);

        return orderResponseDtos;
    }

    public List<CartResponseDTO> viewCart(int customerId) throws CustomerNotFoundException {
        //check if customer id is valid
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }
        catch(Exception e){
            throw new CustomerNotFoundException("Invalid Customer id !!!");
        }

        List<CartResponseDTO> cartResponseDTOS = new ArrayList<>();

        Cart cart = customer.getCart();

        for(Item item:cart.getListOfItems()){

            CartResponseDTO cartResponseDTO = CartResponseDTO.builder()
                    .productName(item.getProduct().getName())
                    .price(item.getProduct().getPrice())
                    .requiredQuantity(item.getRequiredQuantity())
                    .build();
            cartResponseDTOS.add(cartResponseDTO);
        }

        return cartResponseDTOS;
    }
}
