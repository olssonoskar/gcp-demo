package com.olsson.gcpdemo.model;

import com.google.cloud.firestore.annotation.DocumentId;
import lombok.Data;
import org.springframework.cloud.gcp.data.firestore.Document;

import java.util.List;

@Data
@Document(collectionName = "orders")
public class Order {

    @DocumentId
    private long id;
    private List<Product> cart;
    private String user;
    private Status status;

}
