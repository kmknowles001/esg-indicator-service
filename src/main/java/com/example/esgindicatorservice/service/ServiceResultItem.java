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

    private String timeStamp = Util.nowTimeStampAsStr();
    private String portfolioId;
    private String securityId;
    private String method;
    private String action;
    private String esgIndicatorCode;
    private List<String> messageList = new ArrayList<>();
    private boolean success = true;

    public ServiceResultItem(String method, String action, String message){
        this.method = method;
        this.action = action;
        this.add(message);
    }

    public ServiceResultItem(String method, String Action, String securityId, String esgIndicatorCode, String message){
        this.method = method;
        this.esgIndicatorCode = esgIndicatorCode;
        this.add(message);
    }

    public ServiceResultItem(Portfolio porfolio, String message){
        this.portfolioId = porfolio.getPortfolioId();
        this.add(message);
    }
    public ServiceResultItem(Portfolio porfolio, Security sec, String message){
        this.securityId = sec.getSecurityId();
        this.add(message);
    }

    public ServiceResultItem(Portfolio porfolio, Security sec, String esgIndicatorCode, String message){
        this.securityId = sec.getSecurityId();
        this.esgIndicatorCode = esgIndicatorCode;
        this.add(message);
    }

    public void add(String message){
        String messageText = "[" + Util.nowTimeStampAsStr() + "]: " + message;
        this.messageList.add(messageText);
    }
    public void add(String message, boolean success){
        this.success = success;
        this.add(message);
    }
}
