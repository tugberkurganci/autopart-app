package com.itg.autopart.service;

import com.itg.autopart.entities.User;
import com.itg.autopart.mappers.ModelMapperService;
import com.itg.autopart.repository.UserRepository;
import com.itg.autopart.requests.CreateUserRequest;
import com.itg.autopart.requests.UpdateUserRequest;
import com.itg.autopart.responses.GetAllUsersResponse;
import com.itg.autopart.responses.GetByIdUserResponse;
import com.itg.autopart.rules.UserBusinessRules;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private ModelMapperService userMapperService;
    private UserBusinessRules userBusinessRules;

    @Override
    public List<GetAllUsersResponse> findAll() {
        log.info("getting all users...");
        List<User> users = userRepository.findAll();
        List<GetAllUsersResponse> usersResponse = users.stream()
                .map(user -> this.userMapperService.forResponse()
                        .map(user, GetAllUsersResponse.class)).collect(Collectors.toList());
        log.info("Orders: {}", usersResponse);
        return usersResponse;
    }

    @Override
    public GetByIdUserResponse findById(int theId) {

        log.info("getting user by ud...");
        User user = this.userRepository.findById(theId).orElseThrow();
        GetByIdUserResponse response = this.userMapperService.forResponse().map(user, GetByIdUserResponse.class);
        log.info("Orders: {}", response);
        return response;
    }

    @Override
    public void add(CreateUserRequest createUserRequest) {

        log.info("creating fake user...");
        this.userBusinessRules.checkIfUserNameExists(createUserRequest.getName());
        User user = this.userMapperService.forRequest().map(createUserRequest, User.class);
        this.userRepository.save(user);
        log.info("created fake user...");
    }

    @Override
    public void update(UpdateUserRequest updateUserRequest) {
        log.info("updating user...");
        //email name check=========
        User user = this.userMapperService.forRequest().map(updateUserRequest, User.class);
        this.userRepository.save(user);
        log.info("updated user...");

    }

    @Override
    public void deleteById(int theId) {
        log.info("deleting user...");
        this.userRepository.deleteById(theId);
        log.info("deleted user...");
    }

    @Override
    public void increaseFailedAttempt(User user) {
        log.info("increasing failed attempt  ...");
        int newFailedAttempts = user.getFailedAttempt() + 1;
        userRepository.updateFailedAttempt(newFailedAttempts, user.getEmail());
        log.info("increased failed attempt  ...");
    }

    @Override
    public void lock(User user) {
        log.info("locking user  ...");
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());
        userRepository.save(user);
        log.info("locked user  ...");
    }

    @Override
    public void resetFailedAttempts(String email) {
        log.info("reseting failed Attempts   ...");
        userRepository.updateFailedAttempt(0, email);
        log.info("reseted failed Attempts   ...");
    }
}
