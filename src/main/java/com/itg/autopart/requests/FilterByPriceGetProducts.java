package com.itg.autopart.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterByPriceGetProducts {

    private int rangeMinPrice;

    private int rangeMaxPrice;
}
