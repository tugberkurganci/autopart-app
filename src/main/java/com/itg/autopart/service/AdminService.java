package com.itg.autopart.service;

import com.itg.autopart.requests.CreateProductRequest;
import com.itg.autopart.requests.CreateProductRequestAndAdmin;
import com.itg.autopart.requests.GiveAdminPermitRequest;
import com.itg.autopart.requests.MailToAdminRequest;

public interface AdminService {

    void giveAdminPermit(GiveAdminPermitRequest request);

    void addListedProduct(CreateProductRequestAndAdmin createProductRequest);

    void mailToAdmin(MailToAdminRequest mailToAdminRequest);

    void unLockedUser(int userId);
}
