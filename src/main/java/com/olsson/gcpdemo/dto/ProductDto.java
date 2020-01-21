package com.olsson.gcpdemo.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String name;
    private String description;
    private String unit;
    private int amount;

    private String cost;
    private String vat;

}
