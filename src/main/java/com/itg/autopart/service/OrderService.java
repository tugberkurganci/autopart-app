package com.itg.autopart.service;

import com.itg.autopart.requests.CreateOrderRequest;

import com.itg.autopart.responses.GetAllOrderResponse;
import com.itg.autopart.responses.GetAllProductResponse;

import java.util.List;

public interface OrderService {

    List<GetAllOrderResponse> getAll(int userId);

    void add(CreateOrderRequest createOrderRequest);
}
