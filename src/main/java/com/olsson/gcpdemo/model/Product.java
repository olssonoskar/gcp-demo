package com.olsson.gcpdemo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private String name;
    private String description;
    private String unit;
    private int amount;

    private BigDecimal cost;
    private BigDecimal vat;

}
