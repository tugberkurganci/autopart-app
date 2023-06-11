package com.itg.autopart.service;

import com.itg.autopart.entities.CreditCard;
import com.itg.autopart.entities.User;
import com.itg.autopart.mappers.ModelMapperService;
import com.itg.autopart.repository.CreditCardRepository;
import com.itg.autopart.repository.UserRepository;
import com.itg.autopart.requests.CreateCreditCardRequest;
import com.itg.autopart.responses.GetAllCreditCardResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CreditCardServiceImp implements CreditCardService {

    private CreditCardRepository creditCardRepository;

    private ModelMapperService modelMapperService;

    private UserRepository userRepository;


    @Override
    public List<GetAllCreditCardResponse> getAll(int userId) {
        log.info("getting all creditcards...");
        User user = userRepository.findById(userId).orElseThrow();

        List<CreditCard> creditCards = user.getCreditCards();

        List<GetAllCreditCardResponse> responses = creditCards.stream().map(creditCard -> modelMapperService.forResponse().map(creditCard, GetAllCreditCardResponse.class)).collect(Collectors.toList());

        log.info("Response: {}", responses);
        return responses;
    }

    @Override
    public void add(CreateCreditCardRequest createCardRequest) {
        log.info("creating credit card");
        CreditCard creditCard = this.modelMapperService.forRequest().map(createCardRequest, CreditCard.class);
        creditCardRepository.save(creditCard);
        log.info("created credit card");
    }
}
