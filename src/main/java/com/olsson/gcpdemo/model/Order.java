package com.olsson.gcpdemo.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gcp.data.firestore.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collectionName = "orders")
public class Order {

    @DocumentId
    private String id;
    private long userId;
    private List<Product> cart;
    private Status status;

}
