package com.itg.autopart.controller;


import com.itg.autopart.requests.CreateProductRequest;
import com.itg.autopart.requests.UpdateProductRequest;
import com.itg.autopart.responses.GetAllProductResponse;
import com.itg.autopart.responses.GetByIdProductResponse;
import com.itg.autopart.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping()
    public List<GetAllProductResponse> getAll() {

        return productService.findAll();
    }

    @GetMapping("/{id}")
    public GetByIdProductResponse getProductById(@PathVariable int id) {
        return productService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {

        this.productService.deleteById(id);
    }


}
