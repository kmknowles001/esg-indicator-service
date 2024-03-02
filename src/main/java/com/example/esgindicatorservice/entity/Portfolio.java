package com.example.esgindicatorservice.entity;

import lombok.Getter;
import lombok.Setter;
import com.example.esgindicatorservice.collection.EsgSignalCollection;
import com.example.esgindicatorservice.collection.PositionCollection;
import com.example.esgindicatorservice.service.ServiceResultItem;
import java.util.ArrayList;
import java.util.List;

public class Portfolio {

    //
    // declarations
    //
    @Getter
    private String portfolioId;
    @Setter
    private PositionCollection positions = new PositionCollection();
    @Setter @Getter
    private List<BaseEsgIndicator> esgIndicators = new ArrayList<>();

    //
    // constructor
    //
    public Portfolio(String id){
        this.portfolioId = id;
    }

    //
    // methods
    //

    // calculate weight of positions in portfolio
    public void calcuateWeights(){
        for (Position pos : this.positions.getPositions()){
            pos.setWeight(pos.getMarketValue() / positions.getTotalMarketValue());
        }
    }

    // set security issuers for portfolio
    public ServiceResultItem setIssuerEsgSignals(EsgSignalCollection esgSignalCol){
        ServiceResultItem setEsgResultItem = new ServiceResultItem("setIssuerEsgSignals","initialisation","set security data with esg signals");
        for (Position pos : this.getPositions()){
            Security sec = pos.getSecurity();
            sec.setEsgSignal(esgSignalCol.get(sec.getIssuerId()));
            setEsgResultItem.add("esg signals set for " + sec.getIssuerName());
        }
        return setEsgResultItem;
    }

    public List<Position> getPositions(){
        return this.positions.getPositionList();
    }
    public PositionCollection getPositionCollection(){
      return this.positions;
    }
    public void setPositions(PositionCollection value){
        this.positions = value;
        calcuateWeights();
    }
}
