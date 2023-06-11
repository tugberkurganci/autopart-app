package com.itg.autopart.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAndAdressRequest {


    private String cardOwner;

    private int userId;

    private int orderId;

    private String cardNumber;

    private String validityDate;

    private String isCreditCardSaveable;

    private String address;


}
