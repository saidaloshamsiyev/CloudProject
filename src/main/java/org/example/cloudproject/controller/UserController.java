package org.example.cloudproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.cloudproject.entity.UserEntity;
import org.example.cloudproject.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/save")
    public void userSave(@RequestBody UserEntity userEntity) {
        userService.save(userEntity);
    }

}
