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

    private final OrderRepository orderRepository;

    public Mono<ServerResponse> create(Mono<OrderDto> orderDto) {
        return orderDto
                .map(Converters::convertOrder)
                .flatMap(orderRepository::save)
                .flatMap(order -> ServerResponse.ok().bodyValue(order))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Could not save order" + e));
    }

    public Mono<ServerResponse> findUserOrders(String id) {
        return Mono.just(id)
                .map(Long::parseLong)
                .flatMapMany(orderRepository::findAllByUserId)
                .collectList()
                .flatMap(orders -> ServerResponse.ok().bodyValue(orders))
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Could not fetch orders: " + e))
                .switchIfEmpty(ServerResponse.ok().build());
    }

    public Mono<ServerResponse> remove(String id) {
        return orderRepository.deleteById(id)
                .then(ServerResponse.ok().build())
                .onErrorResume(e -> ServerResponse.badRequest().bodyValue("Could not delete order" + e));
    }

}
