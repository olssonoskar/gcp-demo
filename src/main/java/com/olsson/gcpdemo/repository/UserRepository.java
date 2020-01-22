package com.olsson.gcpdemo.repository;

import com.olsson.gcpdemo.model.User;
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;
import reactor.core.publisher.Mono;

public interface UserRepository extends FirestoreReactiveRepository<User> {

    Mono<User> findByIdentification(long identification);

}
