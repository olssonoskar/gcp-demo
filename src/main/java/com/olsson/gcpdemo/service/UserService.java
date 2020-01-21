package com.olsson.gcpdemo.service;

import com.olsson.gcpdemo.dto.UserDto;
import com.olsson.gcpdemo.model.User;
import com.olsson.gcpdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<ServerResponse> registerUser(Mono<UserDto> newUser) {
        return newUser
                .map(userDto -> new User(userDto.getName(), userDto.getAge()))
                .map(userRepository::save)
                .then(ServerResponse.ok().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Could not save"));
    }

    public Mono<ServerResponse> updateUser(Mono<UserDto> update, String id) {
        return userRepository.findByIdentification(id)
                .zipWith(update, (user, updatedUser) -> {
                    user.setName(updatedUser.getName());
                    user.setAge(updatedUser.getAge());
                    return userRepository.save(user);
                }).then(ServerResponse.ok().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Could not update"));
    }

    public Mono<ServerResponse> getUser(String id) {
        return userRepository.findByIdentification(id)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .switchIfEmpty(ServerResponse.notFound().build());

    }

}
