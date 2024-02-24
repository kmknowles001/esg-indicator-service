package com.example.esgindicatorservice.service;

import com.example.esgindicatorservice.common.GsonUtil;
import com.example.esgindicatorservice.common.Util;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ServiceResult {

    // declarations
    private boolean success;
    private final UUID uniqueId;
    private final String createdDt;

    private List<ServiceResultItem> items =new ArrayList<>();
    private int records = 0;
    private int recordsProcessed = 0;

    // constructor
    public ServiceResult(){
        this.uniqueId = UUID.randomUUID();
        this.success = true;
        this.createdDt = Util.nowAsNumeric();
    }

    // output Json object to be returned to caller
    public JsonObject getJSON(){

        JsonObject returnJson = new JsonObject();
        JsonArray jsArray = new JsonArray();

        for (ServiceResultItem item : this.items){
            String strJson = GsonUtil.toJSON(item);
            JsonObject convertedObject = new Gson().fromJson(strJson, JsonObject.class);
            jsArray.add(convertedObject);
        }
        returnJson.add("response-detail-items",jsArray);
        return returnJson;
    }

    public void add(ServiceResultItem item){
        // if any individual result is false i.e. results in not loading a requested record, return false for the entire request.
        if (item.isSuccess() == false){
            this.success = item.isSuccess();
        }
        items.add(item);
    }

    // accessor methods
    public UUID getUniqueId(){return this.uniqueId;}
    public boolean getSuccess(){return this.success;}
    public void setSuccess(boolean value){this.success = value;}
    public String getCreatedDt(){return this.createdDt;}
    public int getRecords(){return this.records;}
    public void setRecords(int value){this.records = value;}
    public int getRecordsProcessed(){return this.recordsProcessed;}
    public void setRecordsProcessed(int value){this.recordsProcessed = value;}

}


