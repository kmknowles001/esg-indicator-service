package com.example.esgindicatorservice.service;

import com.example.esgindicatorservice.common.Util;
import com.example.esgindicatorservice.entity.Portfolio;
import com.example.esgindicatorservice.entity.Position;
import com.example.esgindicatorservice.entity.Security;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class ServiceResultItem {

    private String portfolioId;
    private String securityId;
    private String timeStamp = Util.nowTimeStampAsStr();
    private List<String> message = new ArrayList<>();
    private boolean success = true;

    public ServiceResultItem(){}
    public ServiceResultItem(String message){
        this.message.add(message);
    }
    public ServiceResultItem(Portfolio porfolio, String message){
        this.portfolioId = porfolio.getPortfolioId();
        this.message.add(message);
    }
    public ServiceResultItem(Portfolio porfolio, Security sec, String message){
        this.portfolioId = porfolio.getPortfolioId();
        this.securityId = sec.getSecurityId();
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
