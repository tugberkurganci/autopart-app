package com.itg.autopart.service;

import com.itg.autopart.entities.ListedProduct;
import com.itg.autopart.entities.Order;
import com.itg.autopart.entities.Product;
import com.itg.autopart.entities.User;
import com.itg.autopart.mappers.ModelMapperService;
import com.itg.autopart.repository.ListedProductRepository;
import com.itg.autopart.repository.OrderRepository;
import com.itg.autopart.repository.ProductRepository;
import com.itg.autopart.repository.UserRepository;
import com.itg.autopart.requests.*;
import com.itg.autopart.responses.GetAllProductResponse;
import com.itg.autopart.security.email.EmailSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ShopServiceImp implements ShopService {

    private OrderRepository orderRepository;
    private ModelMapperService modelMapperService;
    private ProductRepository productRepository;
    private ListedProductService listedProductService;
    private ListedProductRepository listedProductRepository;
    private CreditCardService creditCardService;
    private UserRepository userRepository;
    private EmailSender emailSender;

    @Override
    public void addProductToOrder(BuyProductRequest buyProductRequest) {
        log.info("adding product to Order ...");
        Order order = orderRepository.findById(buyProductRequest.getOrderId()).orElseThrow();

        ListedProduct oldProduct = listedProductRepository.findById(buyProductRequest.getId()).orElseThrow();
        Product product = this.modelMapperService.forRequest().map(buyProductRequest, Product.class);
        product.setName(oldProduct.getName());
        product.setPrice(oldProduct.getPrice());
        productRepository.save(product);
        oldProduct.setQuantity(oldProduct.getQuantity() - buyProductRequest.getQuantity());
        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setId(oldProduct.getId());
        updateProductRequest.setName(oldProduct.getName());
        updateProductRequest.setQuantity(oldProduct.getQuantity());
        updateProductRequest.setPrice(oldProduct.getPrice());
        listedProductService.update(updateProductRequest);
        log.info("added product to Order ...");
    }

    @Override
    public void shoppingTransaction(PaymentAndAdressRequest request) {
        log.info("start shopping transaction ...");
        if (request.getIsCreditCardSaveable().equals("YES")) {

            CreateCreditCardRequest createCreditCardRequest = new CreateCreditCardRequest();
            createCreditCardRequest.setCardNumber(request.getCardNumber());
            createCreditCardRequest.setCardOwner(request.getCardOwner());
            createCreditCardRequest.setValidityDate(request.getValidityDate());
            createCreditCardRequest.setUserId(request.getUserId());
            creditCardService.add(createCreditCardRequest);
        }
        User user = userRepository.findById(request.getUserId()).orElseThrow();
        Order order = orderRepository.findById(request.getOrderId()).orElseThrow();
        List<Product> productList = order.getProductList();
        String email = user.getEmail();
        StringBuilder shoppingInfo = new StringBuilder();
        for (Product product : productList
        ) {
            shoppingInfo.append("Product name: ").append(product.getName()).append("Quantity :").append(product.getQuantity()).append("Price :").append(product.getPrice());
            shoppingInfo.append(" ");
        }
        shoppingInfo.append("SEND TO ADRESS :").append(request.getAddress());
        emailSender.sendShoppingInfo(email, shoppingInfo.toString());
        log.info("finish shopping transaction ...");
    }

    @Override
    public List<GetAllProductResponse> getAllProductsFromOrderByUserId(GetAllProductResponseByUserId getAllProductResponseByUserId) {
        log.info("getAllProductsFromUserId ...");
        List<Order> orders = orderRepository.findByUserId(getAllProductResponseByUserId.getUserId());
        Order order1 = null;
        for (Order order : orders
        ) {
            if (order.getId() == getAllProductResponseByUserId.getOrderId()) order1 = order;
        }
        List<Product> productList = order1.getProductList();
        List<GetAllProductResponse> productResponseList = new ArrayList<>();
        for (Product product : productList
        ) {
            GetAllProductResponse getAllProductResponse = new GetAllProductResponse();
            getAllProductResponse.setId(product.getId());
            getAllProductResponse.setName(product.getName());
            getAllProductResponse.setQuantity(product.getQuantity());
            getAllProductResponse.setPrice(product.getPrice());
            productResponseList.add(getAllProductResponse);
        }

        log.info("Orders: {}", productResponseList);
        return productResponseList;
    }


}
