package com.itg.autopart.security.email;

public interface EmailSender {

    void sendConfirmEmail(String to, String email);
    void sendShoppingInfo(String to, String email);
    void sendUserRequest(String to, String email);
}
