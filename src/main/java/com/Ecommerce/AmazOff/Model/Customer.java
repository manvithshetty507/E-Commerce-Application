package com.Ecommerce.AmazOff.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "customer")

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int age;
    private String name;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String mobNo;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Card> listOfCards = new ArrayList<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Cart> listOfCarts = new ArrayList<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Ordered> listOfOrders = new ArrayList<>();
}
