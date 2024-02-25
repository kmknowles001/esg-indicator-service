package com.example.esgindicatorservice.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    //
    // constructor
    //
    public Portfolio(String id){
        this.portfolioId = id;
    }

    //
    // methods
    //
    public void calcuateWeights(){
        for (Position pos : this.positions.getPositions()){
            pos.setWeight(pos.getMarketValue() / positions.getTotalMarketValue());
        }
    }

    public List<Position> getPositions(){
        return this.positions.getPositionList();
    }

    public PositionCollection getPositionCollection(){
      return this.positions;
    }
}
