package com.example.esgindicatorservice.service;

import com.example.esgindicatorservice.common.Util;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
public class ServiceResponse {

    public static ResponseEntity<String> getResponse(BaseService service, String primaryMessage, ServiceResult result){

        Gson gson = new Gson();
        JsonObject jsResponse = new JsonObject();

        jsResponse.addProperty("service-id",service.getServiceId().toString());
        jsResponse.addProperty("service-instance",service.getServiceInstanceId().toString());
        jsResponse.addProperty("service-name",service.getServiceName().toString());
        jsResponse.addProperty("service-version",service.getServiceVersion().toString());
        jsResponse.addProperty("response-dt", Util.nowAsDateTimeStrBQ());
        jsResponse.addProperty("response-message",primaryMessage);
        if (result != null){
            jsResponse.addProperty("response-status", result.getSuccess());
            jsResponse.add("response-detail",result.getJSON());
        }
        return new ResponseEntity<String>(jsResponse.toString(), HttpStatus.OK);
    }
}
