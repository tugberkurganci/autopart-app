package com.itg.autopart.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiveAdminPermitRequest {

    private int id;
    private String password;
}
