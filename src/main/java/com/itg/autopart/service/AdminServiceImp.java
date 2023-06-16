package com.itg.autopart.service;

import com.itg.autopart.entities.ListedProduct;
import com.itg.autopart.entities.User;
import com.itg.autopart.entities.enums.UserRole;
import com.itg.autopart.mappers.ModelMapperService;
import com.itg.autopart.repository.ListedProductRepository;
import com.itg.autopart.repository.UserRepository;
import com.itg.autopart.requests.CreateProductRequestAndAdmin;
import com.itg.autopart.requests.GiveAdminPermitRequest;
import com.itg.autopart.requests.MailToAdminRequest;
import com.itg.autopart.security.email.EmailSender;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class AdminServiceImp implements AdminService {

    private UserRepository userRepository;
    private ModelMapperService modelMapperService;
    private ListedProductRepository listedProductRepository;
    private EmailSender emailSender;

    @Override
    public void giveAdminPermit(GiveAdminPermitRequest request) {

        log.info("giving adming permit");
        User user = userRepository.findById(request.getId()).orElseThrow();

        if (request.getPassword().equals("special")) {
            user.setUserRole(UserRole.ADMIN);
        } else {
            throw new RuntimeException("you cannot be admin");
        }
        userRepository.save(user);
        log.info("gave adming permit");
    }

    @Override
    public void addListedProduct(CreateProductRequestAndAdmin createProductRequest) {
        log.info("adding listed product");
        ListedProduct product = this.modelMapperService.forRequest().map(createProductRequest, ListedProduct.class);
        this.listedProductRepository.save(product);
        log.info("added listed product");
    }

    @Override
    public void mailToAdmin(MailToAdminRequest mailToAdminRequest) {
        log.info("sending mail to adming");
        List<User> users = userRepository.findAll();
        List<User> adminUsers = new ArrayList<>();

        for (User user : users) {
            if (user.getUserRole().equals(UserRole.ADMIN)) {
                adminUsers.add(user);
            }
        }
        User user = adminUsers.get(0);
        String adminEmail = user.getEmail();
        User whoFromMail=userRepository.findByEmail(mailToAdminRequest.getEmail()).orElseThrow();
        String email = mailToAdminRequest.getContent() + " from " + mailToAdminRequest.getEmail()+ " user ıd :"+whoFromMail.getId();
        emailSender.sendUserRequest(adminEmail, email);
        log.info("sended mail to adming");
    }

    @Override
    public void unLockedUser(int userId) {

        log.info("unlocking user");
        User user = userRepository.findById(userId).orElseThrow();
        user.setAccountNonLocked(true);
        user.setFailedAttempt(0);
        user.setLockTime(null);
        userRepository.save(user);
        log.info("unlocked user");

    }
}
