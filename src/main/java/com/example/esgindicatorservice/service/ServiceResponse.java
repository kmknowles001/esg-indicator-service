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


        jsResponse.addProperty("serviceId",service.getServiceId().toString());
        jsResponse.addProperty("serviceInstance",service.getServiceInstanceId().toString());
        jsResponse.addProperty("serviceName",service.getServiceName().toString());
        jsResponse.addProperty("serviceVersion",service.getServiceVersion().toString());
        jsResponse.addProperty("responseDt", Util.nowAsDateTimeStrBQ());
        jsResponse.addProperty("responseMessage",primaryMessage);
        if (result != null){
            jsResponse.addProperty("portfolioId", result.getPortfolioId());
            jsResponse.addProperty("asOfDate", result.getAsOfDate());
            jsResponse.addProperty("responseStatus", result.isSuccess());
            jsResponse.add("responseDetail",result.getJSON());
        }
        return new ResponseEntity<String>(jsResponse.toString(), HttpStatus.OK);
    }
}
