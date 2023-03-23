package com.Ecommerce.AmazOff.Repository;

import com.Ecommerce.AmazOff.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
