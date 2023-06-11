package com.itg.autopart.controller;

import com.itg.autopart.requests.BuyProductRequest;
import com.itg.autopart.requests.GetAllProductResponseByUserId;
import com.itg.autopart.requests.PaymentAndAdressRequest;
import com.itg.autopart.responses.GetAllProductResponse;
import com.itg.autopart.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shops")
@AllArgsConstructor
public class ShopController {

    private ShopService shopService;

    @PostMapping()
    public void addProductToOrder(@RequestBody() BuyProductRequest buyProductRequest) {
        shopService.addProductToOrder(buyProductRequest);
    }

    @PostMapping(path = "/completeShopping")
    public void completeShop(@RequestBody() PaymentAndAdressRequest request) {
        shopService.shoppingTransaction(request);
    }

    @GetMapping()
    public List<GetAllProductResponse> getAllProductbyOrderfromUserID(@RequestBody() GetAllProductResponseByUserId getAllProductResponseByUserId) {
        return shopService.getAllProductsFromOrderByUserId(getAllProductResponseByUserId);
    }


}
