package com.Ecommerce.AmazOff.Repository;

import com.Ecommerce.AmazOff.Model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedRepository extends JpaRepository<Ordered,Integer> {
}
