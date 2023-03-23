package com.Ecommerce.AmazOff.Repository;

import com.Ecommerce.AmazOff.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}