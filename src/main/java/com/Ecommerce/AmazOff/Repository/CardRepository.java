package com.Ecommerce.AmazOff.Repository;

import com.Ecommerce.AmazOff.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
}
