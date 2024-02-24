package com.example.esgindicatorservice.service;

import com.example.esgindicatorservice.entity.Position;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ServiceResultItem {

    private String positionKey;
    private String securityKey;
    private String portfolioKey;
    private int positionHashCode;

    private boolean success = true;
    private List<String> message = new ArrayList<>();

    public ServiceResultItem(Position pos, int positionHashCode){
        this.positionHashCode = positionHashCode;
    }

    public ServiceResultItem(String message){
        this.message.add(message);
    }

    public void add(String message){
        this.message.add(message);
    }
    public void add(String message, boolean success){
        this.success = success;
        this.message.add(message);
    }
}
