package com.itg.autopart.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCreditCardResponse {

    private int id;
    private String cardOwner;
    private String validityDate;
    private String cardNumber;
}
