package com.itg.autopart.responses;

import com.itg.autopart.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllOrderResponse {

    private int id;
    private List<Product> products;

}