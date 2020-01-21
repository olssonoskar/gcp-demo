package com.olsson.gcpdemo.repository;

import com.olsson.gcpdemo.model.Order;
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;

public interface OrderRepository extends FirestoreReactiveRepository<Order> {

}
