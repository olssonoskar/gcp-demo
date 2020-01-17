package com.olsson.gcpdemo.repository;

import com.olsson.gcpdemo.model.User;
import org.springframework.cloud.gcp.data.firestore.FirestoreReactiveRepository;

public interface UserRepository extends FirestoreReactiveRepository<User> {

}
