package com.itg.autopart.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProductResponse {
    private int id;
    private String name;
    private int price;
    private int quantity;
}
