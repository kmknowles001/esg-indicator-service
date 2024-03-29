package com.example.esgindicatorservice.entity;

import com.example.esgindicatorservice.service.ServiceResult;
import com.example.esgindicatorservice.service.ServiceResultItem;

public class CarbonIndicator extends BaseEsgIndicator {

    //
    // constructors
    //
    public CarbonIndicator(){
        super("carbonfootprint","pai");
    }

    //
    // methods
    @Override
    public Double calc(Portfolio portfolio, ServiceResult result) {
        try {
            Double calcValue = 0.0;
            EsgSignal signal;
            ServiceResultItem calcResult = new ServiceResultItem(portfolio,"calculating: " + this.getIndicatorCode());

            for (Position pos : portfolio.getPositions()) {
                signal = pos.getSecurity().getEsgSignal();
                calcValue = +(pos.getWeight() * signal.getEsgSignalFour() * signal.getEsgSignalTwo());
                calcResult.add("[security]: " + pos.getSecurity().getIssuerName() + ",[weight]:" + pos.getWeight() + ",[esgSignalFour]: " + signal.getEsgSignalFour() + ",[esgSignalTwo]:" +  signal.getEsgSignalTwo());
            }
            this.setIndicatorValue(calcValue);
            calcResult.add("[" + this.getIndicatorCode() + "]" + " for [portfolio]:" + portfolio.getPortfolioId() + " has a value of [" + this.getIndicatorValue().toString() + "]");
            result.add(calcResult);
            return this.getIndicatorValue();
        }catch(Exception e){
            result.add(new ServiceResultItem(portfolio,"unhandled exception calculating [" + this.getIndicatorCode() + "]. Message:" + e.getMessage()));
            return 0.0;
        }
    }
}
