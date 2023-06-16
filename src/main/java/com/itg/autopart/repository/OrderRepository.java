package com.itg.autopart.repository;

import com.itg.autopart.entities.ListedProduct;
import com.itg.autopart.entities.Order;
import com.itg.autopart.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUserId(int id);


}
