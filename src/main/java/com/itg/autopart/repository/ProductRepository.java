package com.itg.autopart.repository;

import com.itg.autopart.entities.CreditCard;
import com.itg.autopart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {


    List<Product> findByOrderId(int id);
}
