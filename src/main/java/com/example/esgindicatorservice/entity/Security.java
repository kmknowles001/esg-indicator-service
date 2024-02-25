package com.example.esgindicatorservice.entity;

import lombok.Data;


// Security
// Desc:
//
// simple representation of a security/instrument entity. includes security type to show inclusion/exclusion and two security identifiers ISIN and BCUSIP

@Data
public class Security {

    //
    // declarations
    //
    private String securityId = java.util.UUID.randomUUID().toString();
    private String securityType;
    private String issuerName;
    private String issuerId;
    private String ISIN;
    private String BCUSIP;
    private EsgSignal esgSignal;

    //
    // constructors
    //
    public Security(){}

    public Security(String securityType, String issueName, String issuerId, String isin, String bcusip){
        this.securityType= securityType;
        this.issuerName = issueName;
        this.issuerId = issuerId;
        this.BCUSIP = bcusip;
        this.ISIN = isin;
    }
}
