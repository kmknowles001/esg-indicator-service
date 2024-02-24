package com.example.esgindicatorservice.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Position {

    private String portfolidId;
    private Date asOfDate;
    private double quantity;
    private double weight;
    private double marketValue;
    private Security security = new Security();

    public Position(String id, Date asOfDate, Double qty, Double weight, Double mktval, Security sec){

        this.portfolidId = id;
        this.asOfDate = asOfDate;
        this.quantity = qty;
        this.weight = weight;
        this.marketValue = mktval;
        this.security = sec;
    }
}
