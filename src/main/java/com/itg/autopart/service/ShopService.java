package com.itg.autopart.service;

import com.itg.autopart.requests.BuyProductRequest;
import com.itg.autopart.requests.GetAllProductResponseByUserId;
import com.itg.autopart.requests.PaymentAndAdressRequest;
import com.itg.autopart.responses.GetAllProductResponse;

import java.util.List;

public interface ShopService {


    void addProductToOrder(BuyProductRequest buyProductRequest);

    void shoppingTransaction(PaymentAndAdressRequest request);

    List<GetAllProductResponse> getAllProductsFromOrderByUserId(GetAllProductResponseByUserId getAllProductResponseByUserId);
}
