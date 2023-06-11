package com.itg.autopart.service;

import com.itg.autopart.entities.User;
import com.itg.autopart.repository.UserRepository;
import com.itg.autopart.requests.CreateUserRequest;
import com.itg.autopart.requests.UpdateUserRequest;
import com.itg.autopart.responses.GetAllUsersResponse;
import com.itg.autopart.responses.GetByIdUserResponse;

import java.util.List;

public interface UserService {


    List<GetAllUsersResponse> findAll();
    GetByIdUserResponse findById(int theId);
    void add(CreateUserRequest createUserRequest);
    void update(UpdateUserRequest updateUserRequest);
    void deleteById(int theId);
    void increaseFailedAttempt(User user);
    void lock(User user);
    void resetFailedAttempts(String email);
}
