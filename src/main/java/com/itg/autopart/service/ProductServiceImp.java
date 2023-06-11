package com.itg.autopart.service;

import com.itg.autopart.entities.Product;
import com.itg.autopart.mappers.ModelMapperService;
import com.itg.autopart.repository.ProductRepository;
import com.itg.autopart.requests.CreateProductRequest;
import com.itg.autopart.requests.UpdateProductRequest;
import com.itg.autopart.responses.GetAllProductResponse;
import com.itg.autopart.responses.GetAllUsersResponse;
import com.itg.autopart.responses.GetByIdProductResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService{

    private ModelMapperService modelMapperService;

    private ProductRepository productRepository;
    @Override
    public List<GetAllProductResponse> findAll() {
        log.info("getting all product...");
        List<Product> products=productRepository.findAll();
        List<GetAllProductResponse> productsResponse =products.stream()
                .map(product->this.modelMapperService.forResponse()
                        .map(product, GetAllProductResponse.class)).collect(Collectors.toList());
        log.info("Response: {}", productsResponse );
        return productsResponse;

    }

    @Override
    public GetByIdProductResponse findById(int theId) {
        log.info("getting by ıd product...");
        Product product=this.productRepository.findById(theId).orElseThrow();

        GetByIdProductResponse response=this.modelMapperService.forResponse().map(product,GetByIdProductResponse.class);
        log.info("Response: {}", response );
        return response;
    }

    @Override
    public void add(CreateProductRequest createProductRequest) {
        log.info("creating product...");
        Product product=this.modelMapperService.forRequest().map(createProductRequest,Product.class);

        this.productRepository.save(product);
        log.info("created product...");
    }

    @Override
    public void update(UpdateProductRequest updateProductRequest) {
        log.info("updating product...");
        Product product=this.modelMapperService.forRequest().map(updateProductRequest,Product.class);
        this.productRepository.save(product);
        log.info("updated product...");
    }

    @Override
    public void deleteById(int theId) {

        log.info("deleting product...");
        this.productRepository.deleteById(theId);
        log.info("deleted product...");
    }
}
