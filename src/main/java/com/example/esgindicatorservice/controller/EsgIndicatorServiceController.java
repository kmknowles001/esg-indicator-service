package com.example.esgindicatorservice.controller;

import com.example.esgindicatorservice.common.Util;
import com.example.esgindicatorservice.service.EsgIndicatorService;
import com.example.esgindicatorservice.service.ServiceResponse;
import com.example.esgindicatorservice.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@Component
@RequestMapping("api/esgindicator/")
public class EsgIndicatorServiceController {

    private EsgIndicatorService service;

    @Autowired
    public EsgIndicatorServiceController(){
        service = new EsgIndicatorService();
        Util.Logger(EsgIndicatorServiceController.class).info("[esg indicator] service started...");
    }

    @GetMapping("/v1/calcPortfolfioEsgIndicator") @CrossOrigin()
    public ResponseEntity<String> getNewPositionStreamId(@RequestParam String portfolioId, @RequestParam String asOfDate){
        ServiceResult result = service.calculateEsgIndicators(portfolioId, asOfDate, true);
        return ServiceResponse.getResponse( service, "ADD_SUCCESS",result);
    }
}
