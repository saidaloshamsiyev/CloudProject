package org.example.cloudproject.service;

import lombok.RequiredArgsConstructor;
import org.example.cloudproject.entity.UserEntity;
import org.example.cloudproject.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void save(UserEntity user) {
        userRepository.save(user);
    }

}
