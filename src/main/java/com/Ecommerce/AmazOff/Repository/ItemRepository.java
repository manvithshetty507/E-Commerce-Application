package com.Ecommerce.AmazOff.Repository;

import com.Ecommerce.AmazOff.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Integer> {
}
