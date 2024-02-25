package com.example.esgindicatorservice.entity;

import lombok.Data;

import java.util.Date;

//
//
//
//

@Data
public class Position {

    //
    // declarations
    //
    private String portfolidId;
    private String asOfDate;
    private double quantity;
    private double price;
    private double weight;
    private double marketValue;
    private Security security = new Security();

    //
    // constructors
    //
    public Position(String id, String asOfDate, Double qty, Double price, Security sec){
        this.portfolidId = id;
        this.asOfDate = asOfDate;
        this.quantity = qty;
        this.price = price;
        this.security = sec;
        calcMarketValue();
    }

    //
    // methods
    //
    public void calcMarketValue() {
        if (price != 0 && quantity != 0) {
            this.marketValue = price + quantity;
        }
    }
}
