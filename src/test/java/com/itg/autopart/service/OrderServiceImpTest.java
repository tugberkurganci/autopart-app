package com.itg.autopart.service;

import com.itg.autopart.entities.Order;
import com.itg.autopart.entities.User;
import com.itg.autopart.mappers.ModelMapperService;
import com.itg.autopart.mappers.ModelMapperServiceImp;
import com.itg.autopart.repository.OrderRepository;
import com.itg.autopart.repository.UserRepository;
import com.itg.autopart.requests.CreateOrderRequest;
import com.itg.autopart.responses.GetAllOrderResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImpTest {

    private ModelMapperServiceImp modelMapperService = new ModelMapperServiceImp(new ModelMapper());

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderRepository orderRepository;

    private OrderServiceImp orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderServiceImp(modelMapperService,userRepository,orderRepository);
    }

    @Test
    void getAll_shouldReturnOrders_whenDataExists(){

        //GIVEN
        List<Order>orders=new ArrayList<>();
        int userId=1;
        orders.add(new Order());

        when(orderRepository.findByUserId(userId)).thenReturn(orders);

        //WHEN
        List<GetAllOrderResponse> responses = orderService.getAll(userId);

        //THEN
        verify(orderRepository).findByUserId(userId);
        Assertions.assertEquals(responses.size(),1);
    }
    @Test
    void add_shouldAddOrder_whenInputIsValid(){
        // given
        CreateOrderRequest createOrderRequest=new CreateOrderRequest();
        createOrderRequest.setUserId(0);

        when(orderRepository.save(any())).thenReturn(new Order());

        // when
        orderService.add(createOrderRequest);

        // then
        verify(orderRepository).save(any());
    }

}