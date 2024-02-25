package com.example.esgindicatorservice.entity;

import com.example.esgindicatorservice.service.ServiceResultItem;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class PositionCollection {

    //
    // declarations
    //

    private List<Position> positionList;
    private boolean lookThrough;
    private Double totalMarketValue = 0.0;

    //
    // constructors
    //
    public PositionCollection(){
        positionList = new ArrayList<Position>();
    }

    //
    // methods
    //

    public void add(Position p){
        totalMarketValue+= p.getMarketValue();
        this.positionList.add(p);
    }

    //
    // properties
    //
    public List<Position> getPositions(){
        return positionList;
    }

    public int PositionCount(){
        if(positionList != null){
            return positionList.size();
        }
        return 0;
    }
}
