package com.example.esgindicatorservice.collection;

import com.example.esgindicatorservice.entity.Position;
import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class PositionCollection {

    //
    // declarations
    //

    private List<Position> positionList;
    private String positionSource;
    private Double totalMarketValue = 0.0;
    private Double excludedTotalMarketValue = 0.0;

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
