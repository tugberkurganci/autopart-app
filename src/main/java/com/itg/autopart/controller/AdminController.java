package com.itg.autopart.controller;

import com.itg.autopart.requests.CreateProductRequestAndAdmin;
import com.itg.autopart.requests.GiveAdminPermitRequest;
import com.itg.autopart.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;

    @PutMapping
    public void giveAdminPermit(@RequestBody GiveAdminPermitRequest request){
        this.adminService.giveAdminPermit(request);
    }

    @PutMapping(path = "/unlockedUser/{userId}")
    public void unLockedUser(@PathVariable int userId ){
        this.adminService.unLockedUser(userId);
    }

    @PostMapping
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    public void add(@RequestBody() @Valid() CreateProductRequestAndAdmin createProductRequest){
        this.adminService.addListedProduct(createProductRequest);
    }

}
