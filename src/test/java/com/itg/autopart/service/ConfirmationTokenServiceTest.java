package com.itg.autopart.service;

import com.itg.autopart.repository.ConfirmationTokenRepository;
import com.itg.autopart.security.token.ConfirmationToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ConfirmationTokenServiceTest {

    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;
    @InjectMocks
    private ConfirmationTokenService tokenService;

    @BeforeEach
    public void setup() {

        tokenService = new ConfirmationTokenService(confirmationTokenRepository);
    }

    @Test
    void testSaveConfirmationToken() {
        // given
        ConfirmationToken token = new ConfirmationToken();

        when(confirmationTokenRepository.save(any())).thenReturn(token);

        //when
        tokenService.saveConfirmationToken(token);

        // then
        verify(confirmationTokenRepository).save(any());
    }
    @Test
    void testGetToken() {
        // given
        String token = "testToken";
        ConfirmationToken expectedToken = new ConfirmationToken();
        when(confirmationTokenRepository.findByToken(token)).thenReturn(Optional.of(expectedToken));

        // when
        Optional<ConfirmationToken> actualToken = tokenService.getToken(token);

        // then
        assertTrue(actualToken.isPresent());
        assertEquals(expectedToken, actualToken.get());

        verify(confirmationTokenRepository, times(1)).findByToken(token);
    }
    @Test
    public void testSetConfirmedAt() {

        //given
        String token = "testToken";

        when(confirmationTokenRepository.updateConfirmedAt(eq(token), any(LocalDateTime.class))).thenReturn(1);

        // when
        int result = tokenService.setConfirmedAt(token);

        // then
        verify(confirmationTokenRepository, times(1)).updateConfirmedAt(eq(token), any(LocalDateTime.class));
    }


}