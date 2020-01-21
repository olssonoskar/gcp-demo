package com.olsson.gcpdemo;

import com.olsson.gcpdemo.dto.OrderDto;
import com.olsson.gcpdemo.dto.UserDto;
import com.olsson.gcpdemo.service.OrderService;
import com.olsson.gcpdemo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

@Service
@RequiredArgsConstructor
public class Router {

    private final UserService userService;
    private final OrderService orderService;

    @Bean
    RouterFunction<ServerResponse> routes () {
        return userRoute.and(orderRoute);
    }

    RouterFunction<ServerResponse> userRoute =
            RouterFunctions.route(POST("users/create"),
                    req -> userService.registerUser(req.bodyToMono(UserDto.class)))
                    .andRoute(GET("users/{id}"),
                            req -> userService.getUser(req.pathVariable("id")))
                    .andRoute(PUT("users/{id}"),
                            req -> userService.updateUser(req.bodyToMono(UserDto.class), req.pathVariable("id"))
                    );

    RouterFunction<ServerResponse> orderRoute =
            RouterFunctions.route(POST("order/create"),
                    req -> orderService.create(req.bodyToMono(OrderDto.class)))
                    .andRoute(DELETE("order/{id}"),
                            req -> orderService.remove(req.pathVariable("id")));

}
