package com.Ecommerce.AmazOff.Service;

import com.Ecommerce.AmazOff.DTO.CardDTO;
import com.Ecommerce.AmazOff.DTO.RequestDTO.CardRequestDTO;
import com.Ecommerce.AmazOff.DTO.RequestDTO.CustomerRequestDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.CardResponseDTO;
import com.Ecommerce.AmazOff.DTO.ResponseDTO.CustomerResponseDTO;
import com.Ecommerce.AmazOff.Exception.CustomerNotFoundException;
import com.Ecommerce.AmazOff.Model.Card;
import com.Ecommerce.AmazOff.Model.Cart;
import com.Ecommerce.AmazOff.Model.Customer;
import com.Ecommerce.AmazOff.Repository.CardRepository;
import com.Ecommerce.AmazOff.Repository.CustomerRepository;
import com.Ecommerce.AmazOff.convertor.CardConvertor;
import com.Ecommerce.AmazOff.convertor.CustomerConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CardRepository cardRepository;

    public CustomerResponseDTO addCustomer(CustomerRequestDTO customerRequestDTO) {

        Customer customer = CustomerConvertor.customerRequestDTOtoCustomer(customerRequestDTO);

        //allocate cart to customer
        Cart cart = new Cart();
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        //set cart to customer
        customer.setCart(cart);
        customerRepository.save(customer);

        CustomerResponseDTO customerResponseDTO = CustomerConvertor.customerToCustomerResponseDTO(customer);
        return customerResponseDTO;
    }

    public CardResponseDTO addCard(CardRequestDTO cardRequestDTO) throws CustomerNotFoundException {

        Customer customer;
        try{
            customer = customerRepository.findById(cardRequestDTO.getCustomerId()).get();
        }catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer Id");
        }
        //Make a card Object
        Card card = CardConvertor.cardRequestDTOtoCard(cardRequestDTO);
        card.setCustomer(customer);

        customer.getListOfCards().add(card);
        customerRepository.save(customer);

        //prepare responseDTO
        CardResponseDTO cardResponseDTO = new CardResponseDTO();
        cardResponseDTO.setName(customer.getName());

        //make cardDTOs to be added to list is the second parameter

        List<CardDTO> DTOs = new ArrayList<>();
        for(Card currentCard:customer.getListOfCards()){
            CardDTO cardDTO =new CardDTO();
            cardDTO.setCardNo(currentCard.getCardNo());
            cardDTO.setCardType(currentCard.getCardType());

            DTOs.add(cardDTO);
        }

        cardResponseDTO.setCardDTOs(DTOs);

        return cardResponseDTO;
    }

    public CustomerResponseDTO getCustomerById(int customerId) throws CustomerNotFoundException {
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer id!!");
        }

        CustomerResponseDTO customerResponseDTO = CustomerConvertor.customerToCustomerResponseDTO(customer);
        return customerResponseDTO;
    }

    public List<CustomerResponseDTO> getAllCustomers(){
        List<CustomerResponseDTO> customerResponseDTOS = new ArrayList<>();

        List<Customer> customers = customerRepository.findAll();

        for(Customer customer:customers){
            CustomerResponseDTO DTO = CustomerConvertor.customerToCustomerResponseDTO(customer);
            customerResponseDTOS.add(DTO);
        }
        return customerResponseDTOS;
    }

    public CustomerResponseDTO getCustomerByEmail(String email) throws CustomerNotFoundException {
        Customer customer;
        try{
            customer = customerRepository.findByEmail(email);
        }catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer email!!");
        }
        CustomerResponseDTO customerResponseDTO = CustomerConvertor.customerToCustomerResponseDTO(customer);
        return customerResponseDTO;
    }

    public void deleteById(int id) {
        customerRepository.deleteById(id);
    }

    public CustomerResponseDTO updateCustomerById(int id, CustomerRequestDTO customerRequestDTO) throws CustomerNotFoundException {
        Customer customer;
        try{
            customer = customerRepository.findById(id).get();
        }catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer id!!");
        }

        customer.setName(customerRequestDTO.getName());
        customer.setAge(customerRequestDTO.getAge());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setMobNo(customerRequestDTO.getMobNo());

        customerRepository.save(customer);

        //response DTO

        CustomerResponseDTO customerResponseDTO = CustomerConvertor.customerToCustomerResponseDTO(customer);
        return customerResponseDTO;
    }

    public void deleteCardById(int customerId,int cardId) throws Exception{
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer id!!");
        }
        List<Card> cards = customer.getListOfCards();

        for(Card card:cards){
            if(card.getId()==cardId){
                cardRepository.deleteById(cardId);
            }
        }
        customerRepository.save(customer);
    }

    public void deleteCards(int customerId) throws CustomerNotFoundException {
        Customer customer;
        try{
            customer = customerRepository.findById(customerId).get();
        }catch(Exception e){
            throw new CustomerNotFoundException("Invalid customer id!!");
        }
        List<Card> cards = cardRepository.findAll();
        for(Card card:cards){
            if(card.getCustomer().getId() == customerId){
                cardRepository.delete(card);
            }
        }
        customerRepository.save(customer);
    }
}
