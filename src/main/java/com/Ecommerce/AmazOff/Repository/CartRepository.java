package com.Ecommerce.AmazOff.Repository;

import com.Ecommerce.AmazOff.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
