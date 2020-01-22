package com.olsson.gcpdemo.repository;

import com.olsson.gcpdemo.model.Order;
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;
import reactor.core.publisher.Flux;

public interface OrderRepository extends FirestoreReactiveRepository<Order> {
    Flux<Order> findAllByUserId(long userId);
}
