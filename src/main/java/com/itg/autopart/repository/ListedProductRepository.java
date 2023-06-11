package com.itg.autopart.repository;

import com.itg.autopart.entities.ListedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListedProductRepository extends JpaRepository<ListedProduct,Integer> {

    Optional<ListedProduct> findByName(String name);
}
