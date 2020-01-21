package com.olsson.gcpdemo.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import org.springframework.cloud.gcp.data.firestore.Document;

@Data
@Document(collectionName = "users")
public class User {

    @DocumentId
    private long id;

    private long identification;
    private String name;
    private int age;

    public User(String name, int age) {
        // Might not be supported?
        if (age < 0 ) {
            throw new IllegalArgumentException("Age must be a positive number");
        }

        this.name = name;
        this.age = age;
    }

}
