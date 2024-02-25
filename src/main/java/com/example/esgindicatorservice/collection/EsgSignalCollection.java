package com.example.esgindicatorservice.collection;
import com.example.esgindicatorservice.entity.EsgSignal;

import java.util.HashMap;
import java.util.Map;

public class EsgSignalCollection {

    //
    // declarations
    //
    private Map<String, EsgSignal> esgSignalCollection = new HashMap<String, EsgSignal>();

    //
    // constructor
    //
    public EsgSignalCollection(){}

    //
    // methods
    //
    public void add(EsgSignal signal){
        esgSignalCollection.put(signal.getIssuerId(),signal);
    }

    public EsgSignal get(String issuerId){
       return esgSignalCollection.get(issuerId);
    }

    public int Size(){
        return esgSignalCollection.size();
    }
}
