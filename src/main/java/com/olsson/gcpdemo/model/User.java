package com.olsson.gcpdemo.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import org.springframework.cloud.gcp.data.firestore.Document;

import java.util.UUID;

@Data
@Document(collectionName = "users")
public class User {

    @DocumentId
    private UUID id;
    private String name;
    private Integer age;

}
