package com.itg.autopart.controller;

import com.itg.autopart.requests.MailToAdminRequest;
import com.itg.autopart.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userToAdmin")
@AllArgsConstructor
public class UserToAdminController {

    private AdminService adminService;

    @GetMapping
    public void emailToAdmin(@RequestBody MailToAdminRequest mailToAdminRequest){
        this.adminService.mailToAdmin(mailToAdminRequest);
    }
}
