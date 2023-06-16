package com.itg.autopart.repository;

import com.itg.autopart.entities.CreditCard;
import com.itg.autopart.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard,Integer> {

    List<CreditCard> findByUserId(int id);

}
