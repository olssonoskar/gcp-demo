package com.olsson.gcpdemo.model;

import lombok.Getter;

public enum Status {

    CONFIRMED("Confirmed"),
    HANDLING("Handling"),
    SHIPPED("Shipped"),
    DELIVERD("Delivered");

    @Getter
    private String name;

    Status(String status) {
        this.name = status;
    }
}
