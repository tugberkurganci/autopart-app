package com.itg.autopart.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {

    private int id;
    private String name;
    private int price;
    private int quantity;
}
