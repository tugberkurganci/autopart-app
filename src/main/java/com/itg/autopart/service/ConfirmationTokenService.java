package com.itg.autopart.service;


import com.itg.autopart.repository.ConfirmationTokenRepository;
import com.itg.autopart.security.token.ConfirmationToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ConfirmationTokenService {

    private ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token){
        log.info("saving confirmation token");

        confirmationTokenRepository.save(token);
        log.info("saved confirmation token");
    }
    public Optional<ConfirmationToken> getToken(String token) {
        log.info("getting token");
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        log.info("setting confirmation token");
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
