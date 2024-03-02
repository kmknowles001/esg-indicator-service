package com.example.esgindicatorservice.service;

import com.example.esgindicatorservice.collection.EsgSignalCollection;
import com.example.esgindicatorservice.data.EsgIndicatorRepo;
import com.example.esgindicatorservice.entity.*;
import java.util.UUID;

public class EsgIndicatorService extends BaseService {

    //
    // constructors
    //
    public EsgIndicatorService(){
        this.setServiceId(UUID.randomUUID());
        this.setServiceName("EsgIndicatorService");
        this.setServiceVersion("V1.0");
    }

    //
    //  methods
    //

    // primary method to calculate
    public ServiceResult calculateEsgIndicators(String portfolioId, String asOfDate, boolean debug){

        Portfolio portfolio = new Portfolio(portfolioId);   // get portfolio details (Simulate Product Master API)
        ServiceResult result = new ServiceResult(portfolioId, asOfDate); // return API with response and debug.

        // 1. get portfolio including positions (simulate position API)
        portfolio.setPositions(EsgIndicatorRepo.getPositions(portfolio.getPortfolioId() , asOfDate));
        result.add(new ServiceResultItem("getPositions","initialisation","loaded " + portfolio.getPositionCollection().PositionCount() + " positions from " +  portfolio.getPositionCollection().getPositionSource() + " as of " + asOfDate));

        // 2. get portfolio esg indicators to calculate.
        portfolio.setEsgIndicators(EsgIndicatorRepo.getESGIndicators());
        result.add(new ServiceResultItem("getESGIndicators","initialisation","loaded " + portfolio.getEsgIndicators().size() + " esg signals"));

        // 3. get esg issuer signals (simulate esg signals API)
        EsgSignalCollection esgSignalCollection = EsgIndicatorRepo.getIssuerSignals();
        result.add(new ServiceResultItem("getIssuerSignals","initialisation","loaded " + esgSignalCollection.Size() + " esg signals."));

        // 4. set each positions security issuer signals
        result.add(portfolio.setIssuerEsgSignals(esgSignalCollection));

        // 5. validate
        result.add(validate(portfolio));

        // 6. calc
        calc(portfolio, result);

        return result;
    }

    //
    // calc esg indicators
    //
    public boolean calc(Portfolio portfolio, ServiceResult result){
        for ( BaseEsgIndicator indicator : portfolio.getEsgIndicators()){
            indicator.calc(portfolio, result);
        }
        return result.isSuccess();
    }

    //
    // validate data position & security data - before we start.
    //
    public ServiceResultItem validate(Portfolio portfolio){

        ServiceResultItem validationResult = new ServiceResultItem("validate","validation","validate security issuer and identifiers.");

        for(Position pos : portfolio.getPositions()){
            Security sec = pos.getSecurity();
            validationResult.add("validating security " + sec.getIssuerName());

            // critical //
            if (sec.getIssuerId() == null) {
                validationResult.add("[CRITICAL]: Security is missing an issuer.");
                validationResult.setSuccess(false); // critical issue
            }
            if (sec.getBCUSIP() == null && sec.getISIN() == null) {
                validationResult.add("[CRITICAL]: Security is missing both critical identifiers.");
                validationResult.setSuccess(false); // critical issue
            }

            // non-critical //
            if (sec.getISIN() == null || sec.getISIN().length() == 0) {
                validationResult.add("[WARNING]: Security is missing an ISIN.");
//                validationResult.add(new ServiceResultItem(portfolio, pos.getSecurity(),"security is missing an ISIN."));
            }
            if (sec.getBCUSIP() == null || sec.getBCUSIP().length() == 0) {
                validationResult.add("[WARNING]: Security is missing an CUSIP.");
            }
        }
        return validationResult;
    }
}
