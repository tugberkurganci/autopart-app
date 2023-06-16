package com.itg.autopart.service;

import com.itg.autopart.entities.Order;
import com.itg.autopart.entities.User;
import com.itg.autopart.mappers.ModelMapperService;
import com.itg.autopart.repository.OrderRepository;
import com.itg.autopart.repository.UserRepository;
import com.itg.autopart.requests.CreateOrderRequest;
import com.itg.autopart.responses.GetAllOrderResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImp implements OrderService {

    private ModelMapperService modelMapperService;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Override
    public List<GetAllOrderResponse> getAll(int userId) {

        log.info("getting all orders...");

        List<Order> orders =orderRepository.findByUserId(userId);
        List<GetAllOrderResponse> responses = orders.stream().map(order -> modelMapperService.forResponse().map(order, GetAllOrderResponse.class))
                .collect(Collectors.toList());
        log.info("Orders: {}", responses);
        return responses;
    }

    @Override
    public void add(CreateOrderRequest createOrderRequest) {
        log.info("create order...");
        Order order = this.modelMapperService.forRequest().map(createOrderRequest, Order.class);
        orderRepository.save(order);
        log.info("created order");
    }
}
