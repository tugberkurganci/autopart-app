package com.itg.autopart.security.config;

import com.itg.autopart.entities.User;
import com.itg.autopart.repository.UserRepository;
import com.itg.autopart.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

        private final int maxAttempt=5;
        @Autowired
        private UserService userService;

        @Autowired
        private  UserRepository userRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String email=request.getParameter("username");
        User user=userRepository.findByEmail(email).orElseThrow();

        if (user.isEnabled() && user.isAccountNonLocked()){
            if(user.getFailedAttempt()<maxAttempt-1){
                userService.increaseFailedAttempt(user);
            } else {
               userService.lock(user);
               exception=new LockedException("Your account locked due to 5 failed attempt");
            }
        }
        super.setDefaultFailureUrl("/login?error");
        super.onAuthenticationFailure(request, response, exception);
    }
}
