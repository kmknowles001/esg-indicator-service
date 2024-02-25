package com.example.esgindicatorservice.entity;
import lombok.Data;

@Data
public class GHGIndicator extends ESGIndicator{
    public GHGIndicator(){
        super("ghgemissions", "pai");
    }

    @Override
    public void calc() {



        super.calc();
    }
}