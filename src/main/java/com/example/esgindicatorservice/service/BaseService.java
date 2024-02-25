package com.example.esgindicatorservice.service;

import com.google.gson.Gson;
import lombok.Data;

import java.util.UUID;

@Data
public class BaseService {

    private UUID serviceInstanceId = UUID.randomUUID();
    private UUID serviceId;
    private String serviceName;
    private String serviceVersion;
    private String projectId;

    public BaseService(){}

    public String toJSON() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getServiceDetails(){
        return toJSON();
    }
}
