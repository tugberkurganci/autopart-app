package com.itg.autopart.service;

import com.itg.autopart.requests.CreateProductRequest;
import com.itg.autopart.requests.UpdateProductRequest;
import com.itg.autopart.responses.GetAllProductResponse;
import com.itg.autopart.responses.GetByIdProductResponse;

import java.util.List;

public interface ProductService {

    List<GetAllProductResponse> findAll();

    GetByIdProductResponse findById(int theId);

    void add(CreateProductRequest createProductRequest);

    void update(UpdateProductRequest updateProductRequest);

    void deleteById(int theId);
}
