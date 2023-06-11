package com.itg.autopart.controller;


import com.itg.autopart.requests.CreateProductRequest;
import com.itg.autopart.requests.FilterByPriceGetProducts;
import com.itg.autopart.requests.UpdateProductRequest;
import com.itg.autopart.responses.GetAllProductResponse;
import com.itg.autopart.responses.GetByIdProductResponse;
import com.itg.autopart.responses.GetByNameProductResponse;
import com.itg.autopart.service.ListedProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/listProducts")
@AllArgsConstructor
public class ListedProductController {

    private ListedProductService productService;

    @GetMapping()
    public List<GetAllProductResponse> getAll() {

        return productService.findAll();
    }
    @GetMapping("/filterByPrice")
    public List<GetAllProductResponse> getFilteredPrices(@RequestBody() FilterByPriceGetProducts filterByPriceGetProducts) {

        return productService.filterByInPrices(filterByPriceGetProducts);
    }

    @GetMapping("/{id}")
    public GetByIdProductResponse getProductById(@PathVariable int id) {
        return productService.findById(id);
    }

    @GetMapping("/name/{name}")
    public GetByNameProductResponse getProductByName(@PathVariable String  name) {
        return productService.findByName(name);
    }

    @PostMapping()
    @ResponseStatus(code= HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateProductRequest createProductRequest){

        this.productService.add(createProductRequest);
    }

    @PutMapping
    public void update(@RequestBody UpdateProductRequest updateProductRequest){


        this.productService.update(updateProductRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id){

        this.productService.deleteById(id);
    }



}
