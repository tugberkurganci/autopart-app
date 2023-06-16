package com.itg.autopart.service;

import com.itg.autopart.entities.CreditCard;
import com.itg.autopart.mappers.ModelMapperServiceImp;
import com.itg.autopart.repository.CreditCardRepository;
import com.itg.autopart.repository.OrderRepository;
import com.itg.autopart.repository.UserRepository;
import com.itg.autopart.responses.GetAllCreditCardResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceImpTest {

    private ModelMapperServiceImp modelMapperService = new ModelMapperServiceImp(new ModelMapper());

    @Mock
    private UserRepository userRepository;

    @Mock
    private CreditCardRepository creditCardRepository;

    private CreditCardServiceImp creditCardService;

    @BeforeEach
    void setUp() {
        creditCardService = new CreditCardServiceImp(creditCardRepository, modelMapperService, userRepository);
    }


    @Test
    void getAll_shouldReturnCreditCards_whenInputValids(){

        List<CreditCard> creditCards=new ArrayList<>();

        when(creditCardRepository.findByUserId(1)).thenReturn(creditCards);

        List<GetAllCreditCardResponse> response= creditCardService.getAll(1);


        verify(creditCardRepository).findByUserId(1);
        assertEquals(0,response.size());
    }

}