package com.itg.autopart.requests;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RegistrationRequest {


    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    private String email;

    private String password;

}