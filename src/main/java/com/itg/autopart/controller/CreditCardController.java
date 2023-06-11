package com.itg.autopart.controller;

import com.itg.autopart.requests.CreateCreditCardRequest;
import com.itg.autopart.responses.GetAllCreditCardResponse;
import com.itg.autopart.service.CreditCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/creditcards")
@AllArgsConstructor
public class CreditCardController {
    private CreditCardService creditCardService;

    @GetMapping("/{userId}")
    public List<GetAllCreditCardResponse> getCardsById(@PathVariable int userId) {
         return creditCardService.getAll(userId);
    }

    @PostMapping()
    @ResponseStatus(code= HttpStatus.CREATED)
    public void add(@RequestBody()  CreateCreditCardRequest createCreditCardRequest) {
        this.creditCardService.add(createCreditCardRequest);
    }

}
