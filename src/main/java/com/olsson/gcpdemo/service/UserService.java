package com.olsson.gcpdemo.service;

import com.olsson.gcpdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<String> greetUser(String id) {
        return userRepository.findById(id)
            .map(user -> "Welcome " + user.getName());
    }

}
