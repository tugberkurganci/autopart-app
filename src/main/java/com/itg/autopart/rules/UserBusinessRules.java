package com.itg.autopart.rules;

import com.itg.autopart.exceptions.BusinessException;
import com.itg.autopart.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserBusinessRules {

    private UserRepository userRepository;

    public void checkIfUserNameExists(String name){

        if (this.userRepository.existsByName(name)){
            throw new BusinessException("User name already exists");
        }
    }
}
