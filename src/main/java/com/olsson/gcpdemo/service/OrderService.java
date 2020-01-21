package com.olsson.gcpdemo.service;

import com.olsson.gcpdemo.dto.OrderDto;
import com.olsson.gcpdemo.model.converters.Converters;
import com.olsson.gcpdemo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class OrderService {

    OrderRepository orderRepository;

    public Mono<ServerResponse> create(Mono<OrderDto> orderDto) {
        return orderDto
                .map(Converters::convertOrder)
                .map(order -> orderRepository.save(order))
                .then(ServerResponse.ok().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Could not save order"));
    }

    public Mono<ServerResponse> remove(String id) {
        return orderRepository.deleteById(id)
                .then(ServerResponse.ok().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Could not delete order"));
    }

}
