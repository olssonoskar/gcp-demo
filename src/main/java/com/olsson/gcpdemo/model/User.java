package com.olsson.gcpdemo.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.firestore.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collectionName = "users")
public class User {

    @DocumentId
    private String id;
    private long identification;
    private String name;
    private int age;

}
