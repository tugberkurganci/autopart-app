package com.itg.autopart.service;

import com.itg.autopart.entities.ListedProduct;
import com.itg.autopart.entities.Order;
import com.itg.autopart.entities.Product;
import com.itg.autopart.mappers.ModelMapperServiceImp;
import com.itg.autopart.repository.ListedProductRepository;
import com.itg.autopart.repository.OrderRepository;
import com.itg.autopart.repository.ProductRepository;
import com.itg.autopart.repository.UserRepository;
import com.itg.autopart.requests.BuyProductRequest;
import com.itg.autopart.security.email.EmailSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShopServiceImpTest {

    private ModelMapperServiceImp modelMapperService = new ModelMapperServiceImp(new ModelMapper());

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ListedProductService listedProductService;
    @Mock
    private ListedProductRepository listedProductRepository;
    @Mock
    private CreditCardService creditCardService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EmailSender emailSender;

    private ShopServiceImp shopServiceImp;
    @BeforeEach
    void setUp() {
        shopServiceImp = new ShopServiceImp(orderRepository,modelMapperService,productRepository,listedProductService,listedProductRepository,creditCardService,userRepository,emailSender);
    }


    @Test
    void addProductToOrder_shouldAddProduct_whenInputValid(){

        BuyProductRequest buyProductRequest=new BuyProductRequest();

        Order order= new Order();

        when(orderRepository.findById(any())).thenReturn(Optional.of(order));
        when(listedProductRepository.findById(any())).thenReturn(Optional.of(new ListedProduct()));
        when(productRepository.save(any())).thenReturn(new Product());
        doNothing().when(listedProductService).update(any());

        shopServiceImp.addProductToOrder(buyProductRequest);

        verify(orderRepository).findById(any());
        verify(listedProductRepository).findById(any());
        verify(productRepository).save(any());
        verify(listedProductService).update(any());


    }
}