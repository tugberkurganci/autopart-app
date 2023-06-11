package com.itg.autopart.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditCardRequest {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String cardOwner;

    @NotNull
    @NotBlank
    private int userId;

    @NotNull
    @NotBlank
    private String cardNumber;

    @NotNull
    @NotBlank
    private String validityDate;


}
