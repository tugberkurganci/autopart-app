package com.itg.autopart.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name="listedproducts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ListedProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private int price;

    @Column(name="quantity")
    private int quantity;


}
