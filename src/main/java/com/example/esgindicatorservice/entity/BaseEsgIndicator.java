package com.example.esgindicatorservice.entity;


import com.example.esgindicatorservice.service.ServiceResult;
import lombok.Data;

@Data
public abstract class BaseEsgIndicator {

    //
    // declarations
    //
    private String indicatorId;
    private String indicatorType;
    private String indicatorCode;
    private Double indicatorValue = 0.0;

    //
    // constructors
    //
    public BaseEsgIndicator(String code, String type){
        indicatorType = type;
        indicatorCode = code;
        indicatorId = type + "-" + code + "-" + java.util.UUID.randomUUID().toString();
    }

    //
    // methods
    //
    public Double calc(Portfolio portfolio, ServiceResult result){
        return 0.0;
    }
}
