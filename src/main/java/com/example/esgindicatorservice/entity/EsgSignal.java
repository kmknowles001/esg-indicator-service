package com.example.esgindicatorservice.entity;

import lombok.Data;

@Data
public class EsgSignal {

    //
    // declarations
    //
    private String issuerId;
    private Double esgSignalOne;
    private Double esgSignalTwo;
    private Double esgSignalThree;
    private Double esgSignalFour;

    //
    // constructor
    //
    public EsgSignal(String issuerId, Double signalOne, Double signalTwo, Double signalThree, Double signalFour){
        this.issuerId=issuerId;
        this.esgSignalOne = signalOne;
        this.esgSignalTwo = signalTwo;
        this.esgSignalThree = signalThree;
        this.esgSignalFour = signalFour;
    }
}
