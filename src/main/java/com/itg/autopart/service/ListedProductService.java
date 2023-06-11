package com.itg.autopart.service;

import com.itg.autopart.requests.CreateProductRequest;
import com.itg.autopart.requests.FilterByPriceGetProducts;
import com.itg.autopart.requests.UpdateProductRequest;
import com.itg.autopart.responses.GetAllProductResponse;
import com.itg.autopart.responses.GetByIdProductResponse;
import com.itg.autopart.responses.GetByNameProductResponse;

import java.util.List;

public interface ListedProductService {

    List<GetAllProductResponse> findAll();

    GetByIdProductResponse findById(int theId);

    GetByNameProductResponse findByName(String name);

    List<GetAllProductResponse> filterByInPrices(FilterByPriceGetProducts filterByPriceGetProducts);

    void add(CreateProductRequest createProductRequest);

    void update(UpdateProductRequest updateProductRequest);

    void deleteById(int theId);
}
