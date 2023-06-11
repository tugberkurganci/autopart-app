package com.itg.autopart.service;

import com.itg.autopart.entities.ListedProduct;
import com.itg.autopart.mappers.ModelMapperService;
import com.itg.autopart.repository.ListedProductRepository;
import com.itg.autopart.requests.CreateProductRequest;
import com.itg.autopart.requests.FilterByPriceGetProducts;
import com.itg.autopart.requests.UpdateProductRequest;
import com.itg.autopart.responses.GetAllProductResponse;
import com.itg.autopart.responses.GetByIdProductResponse;
import com.itg.autopart.responses.GetByNameProductResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ListedProductServiceImp implements ListedProductService{

    private ModelMapperService modelMapperService;
    private ListedProductRepository productRepository;
    @Override
    public List<GetAllProductResponse> findAll() {
        log.info("getting all listedproduct...");
        List<ListedProduct> products=productRepository.findAll();
        List<GetAllProductResponse> productsResponse =products.stream()
                .map(product->this.modelMapperService.forResponse()
                        .map(product, GetAllProductResponse.class)).collect(Collectors.toList());
        log.info("Response: {}", productsResponse );
        return productsResponse;

    }
    @Override
    public List<GetAllProductResponse> filterByInPrices(FilterByPriceGetProducts filterByPriceGetProducts) {

        log.info("filtering orices...");
        List<ListedProduct> products=productRepository.findAll();
        List<ListedProduct> filteredProducts=new ArrayList<>();

        for (ListedProduct product:products
             ) {
            if (product.getPrice()<filterByPriceGetProducts.getRangeMaxPrice() && product.getPrice()>filterByPriceGetProducts.getRangeMinPrice()){
                filteredProducts.add(product);}
        }
        List<GetAllProductResponse> productResponses=filteredProducts.stream().
                map(product ->this.modelMapperService.forResponse().map(product,GetAllProductResponse.class)).collect(Collectors.toList());

        log.info("Response: {}", productResponses );
        return productResponses;
    }

    @Override
    public GetByIdProductResponse findById(int theId) {
        log.info("getting by ıd listedproduct...");
        ListedProduct product=this.productRepository.findById(theId).orElseThrow();

        GetByIdProductResponse response=this.modelMapperService.forResponse().map(product,GetByIdProductResponse.class);
        log.info("Response: {}", response );
        return response;
    }

    @Override
    public GetByNameProductResponse findByName(String name) {
        log.info("getting by name listedproduct...");
        ListedProduct product=this.productRepository.findByName(name).orElseThrow();

        GetByNameProductResponse response=this.modelMapperService.forResponse().map(product,GetByNameProductResponse.class);
        log.info("Response: {}", response );
        return response;
    }



    @Override
    public void add(CreateProductRequest createProductRequest) {
        log.info("creating listedproduct...");
        ListedProduct product=this.modelMapperService.forRequest().map(createProductRequest,ListedProduct.class);
        this.productRepository.save(product);
        log.info("created listedproduct...");
    }

    @Override
    public void update(UpdateProductRequest updateProductRequest) {
        log.info("updating listedproduct...");
        ListedProduct product=this.modelMapperService.forRequest().map(updateProductRequest,ListedProduct.class);
        this.productRepository.save(product);
        log.info("updated listedproduct...");

    }

    @Override
    public void deleteById(int theId) {
        log.info("deleting listedproduct...");
        this.productRepository.deleteById(theId);
        log.info("deleted listedproduct...");
    }
}
