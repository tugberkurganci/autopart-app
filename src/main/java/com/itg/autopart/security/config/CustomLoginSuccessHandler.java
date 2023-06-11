package com.itg.autopart.security.config;

import com.itg.autopart.entities.User;
import com.itg.autopart.repository.UserRepository;
import com.itg.autopart.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String email=request.getParameter("username");
        System.out.println(email);
        User user=userRepository.findByEmail(email).orElseThrow();
        if (user.getFailedAttempt() > 0){
            userService.resetFailedAttempts(user.getEmail());
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
