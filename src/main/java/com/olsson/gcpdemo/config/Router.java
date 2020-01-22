package com.olsson.gcpdemo.config;

import com.olsson.gcpdemo.dto.OrderDto;
import com.olsson.gcpdemo.dto.UserDto;
import com.olsson.gcpdemo.service.OrderService;
import com.olsson.gcpdemo.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;

@Configuration
public class Router {

    public static final String USERS = "users";
    public static final String ORDERS = "orders";
    public static final String ID = "/{id}";
    public static final String ID_PARAM = "id";

    @Bean
    RouterFunction<ServerResponse> routes (UserService userService, OrderService orderService) {
        return userRoute(userService).and(orderRoute(orderService));
    }

    RouterFunction<ServerResponse> userRoute(UserService userService) {
           return RouterFunctions.route(POST(USERS + "/create"),
               req -> userService.registerUser(req.bodyToMono(UserDto.class)))
               .andRoute(GET(USERS + ID),
                   req -> userService.getUser(req.pathVariable(ID_PARAM)))
               .andRoute(GET(USERS), req -> userService.getUsers())
               .andRoute(PUT(USERS + ID),
                   req -> userService.updateUser(req.bodyToMono(UserDto.class), req.pathVariable(ID_PARAM))
               );
    }

    RouterFunction<ServerResponse> orderRoute(OrderService orderService) {
        return RouterFunctions
                .route(POST(ORDERS + "/create"),
                        req -> orderService.create(req.bodyToMono(OrderDto.class)))
                .andRoute(DELETE(ORDERS + ID),
                        req -> orderService.remove(req.pathVariable(ID_PARAM)))
                .andRoute(GET(ORDERS + ID),
                        req -> orderService.findUserOrders(ID_PARAM));
    }
}
