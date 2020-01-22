package com.olsson.gcpdemo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private String name;
    private String description;
    private String unit;
    private int amount;

    // Firestore does not support BigDecimal, for a moneytype we should use String to preserve precision
    private BigDecimal cost;
    private BigDecimal vat;

    public String getCost() {
        return cost.toString();
    }

    public String getVat() {
        return vat.toString();
    }

    public void setCost(String cost) {
        this.cost = new BigDecimal(cost);
    }

    public void setVat(String vat) {
        this.vat = new BigDecimal(vat);
    }

}
