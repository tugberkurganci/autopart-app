package com.itg.autopart.controller;

import com.itg.autopart.requests.CreateUserRequest;
import com.itg.autopart.requests.UpdateUserRequest;
import com.itg.autopart.responses.GetAllUsersResponse;
import com.itg.autopart.responses.GetByIdUserResponse;
import com.itg.autopart.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping()
    public List<GetAllUsersResponse> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public GetByIdUserResponse getById(@PathVariable int id) {
        return userService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public void add(@RequestBody() @Valid() CreateUserRequest createUserRequest) {

        this.userService.add(createUserRequest);
    }

    @PutMapping
    public void update(@RequestBody UpdateUserRequest updateUserRequest) {
        this.userService.update(updateUserRequest);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        this.userService.deleteById(id);
    }


}
