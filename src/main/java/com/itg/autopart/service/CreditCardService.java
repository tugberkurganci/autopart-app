package com.itg.autopart.service;

import com.itg.autopart.requests.CreateCreditCardRequest;
import com.itg.autopart.responses.GetAllCreditCardResponse;

import java.util.List;

public interface CreditCardService {

    List<GetAllCreditCardResponse> getAll(int userId);

    void add(CreateCreditCardRequest createCarRequest);
}
