package com.example.esgindicatorservice.entity;
import com.example.esgindicatorservice.service.ServiceResult;
import com.example.esgindicatorservice.service.ServiceResultItem;
import lombok.Data;

@Data
public class GhgIndicator extends BaseEsgIndicator {

    //
    // constructor
    //
    public GhgIndicator(){
        super("ghgemissions", "pai");
    }

    //
    // methods
    //
    @Override
    public Double calc(Portfolio portfolio, ServiceResult result) {
        try {

            Double calcValue = 0.0;
            EsgSignal signal;
            ServiceResultItem calcResult = new ServiceResultItem(portfolio,"calculating: " + this.getIndicatorCode());

            for (Position pos : portfolio.getPositions()) {
                signal = pos.getSecurity().getEsgSignal();
                calcValue = +(pos.getWeight() * signal.getEsgSignalOne() * signal.getEsgSignalThree());
                calcResult.add("[security]: " + pos.getSecurity().getIssuerName() + ",[weight]:" + pos.getWeight() + ",[esgSignalOne]: " + signal.getEsgSignalOne() + ",[esgSignalThree]:" +  signal.getEsgSignalThree());
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