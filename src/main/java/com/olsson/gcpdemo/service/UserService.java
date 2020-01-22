package com.olsson.gcpdemo.service;

import com.olsson.gcpdemo.dto.UserDto;
import com.olsson.gcpdemo.model.User;
import com.olsson.gcpdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<ServerResponse> registerUser(Mono<UserDto> newUser) {
        return newUser
                .map(userDto -> new User(
                        UUID.randomUUID().toString(),
                        userDto.getIdentification(),
                        userDto.getName(),
                        userDto.getAge()))
                .flatMap(userRepository::save)
                .flatMap(userMono -> ServerResponse.ok().bodyValue(userMono))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Could not save user: " + e));
    }

    public Mono<ServerResponse> updateUser(Mono<UserDto> update, String id) {
        return Mono.just(id)
                .map(Long::parseLong)
                .flatMap(userRepository::findByIdentification)
                .zipWith(update, (user, updatedUser) -> {
                    user.setName(updatedUser.getName());
                    user.setAge(updatedUser.getAge());
                    return userRepository.save(user);
                }).flatMap(mono -> mono)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Could not update user: " + e));
    }

    public Mono<ServerResponse> getUser(String id) {
        return Mono.just(id)
                .map(Long::parseLong)
                .flatMap(userRepository::findByIdentification)
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getUsers() {
        return userRepository.findAll()
            .collectList()
            .flatMap(users -> ServerResponse.ok().bodyValue(users));
    }

}
