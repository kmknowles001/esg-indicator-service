package com.example.esgindicatorservice.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Portfolio {
    private String portfolioId;
    private List<Position> positions = new ArrayList<>();
}
