package com.itg.autopart.requests;


import com.itg.autopart.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFinishOrderRequest {

    private int userid;
    private List<Product> products;
}
