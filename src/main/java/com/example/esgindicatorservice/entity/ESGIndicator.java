package com.example.esgindicatorservice.entity;


import com.example.esgindicatorservice.service.ServiceResult;
import com.example.esgindicatorservice.service.ServiceResultItem;
import lombok.Data;

@Data
public abstract class ESGIndicator {

    //
    // declarations
    //
    private String indicatorId;
    private String indicatorType;
    private String indicatorCode;

    //
    // constructors
    //
    public ESGIndicator(String code, String type){
        indicatorType = type;
        indicatorCode = code;
        indicatorId = type + "-" + code + "-" + java.util.UUID.randomUUID().toString();
    }

    //
    // methods
    //
    public void calc(){

    }

    public void calc(ServiceResult result){

    }
}
