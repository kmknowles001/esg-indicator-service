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

    // primary method.
    public ServiceResult calculateEsgIndicators(String portfolioId, String asOfDate, boolean debug){

        EsgSignalCollection esgSignalCollection = new EsgSignalCollection();
        ServiceResult result = new ServiceResult(portfolioId);
        Portfolio portfolio = new Portfolio(portfolioId);
        ServiceResultItem initialiseServiceResult;



        // 1. get portfolio including positions (simulate position API)
        portfolio.setPositions(EsgIndicatorRepo.getPositions(portfolio.getPortfolioId() , asOfDate));
        initialiseServiceResult = new ServiceResultItem(portfolio,"loaded " + portfolio.getPositionCollection().PositionCount() + " positions from " +  portfolio.getPositionCollection().getPositionSource() + " as of " + asOfDate);
//        result.add(new ServiceResultItem(portfolio,"loaded" + portfolio.getPositionCollection().PositionCount() + " positions from " +  portfolio.getPositionCollection().getPositionSource() + " as of " + asOfDate));

        // 2. get portfolio esg indicators to calculate.
        portfolio.setEsgIndicators(EsgIndicatorRepo.getESGIndicators());
        initialiseServiceResult.add("found " + portfolio.getEsgIndicators().size() + " esg indicators.");
//        result.add(new ServiceResultItem(portfolio,"found " + portfolio.getEsgIndicators().size() + " esg indicators."));

        // 3. get esg issuer signals (simulate esg signals API)
        esgSignalCollection = EsgIndicatorRepo.getIssuerSignals();
        initialiseServiceResult.add("found " + esgSignalCollection.Size() + " esg signals.");
//        result.add(new ServiceResultItem(portfolio,"found esg signals: " + esgSignalCollection.Size()));
        result.add(initialiseServiceResult);

        // 4. set each positions security issuer signals
        portfolio.setIssuerEsgSignals(esgSignalCollection);

        // 5. validate
        validate(portfolio, result);

        // 6. calc
        calc(portfolio, result);

        return result;
    }

    public boolean calc(Portfolio portfolio, ServiceResult result){
        for ( BaseEsgIndicator indicator : portfolio.getEsgIndicators()){
            indicator.calc(portfolio, result);

        }
        return result.getSuccess();
    }

    //
    // validate data position & security data - before we start.
    //
    public void validate(Portfolio portfolio, ServiceResult result){
        for(Position pos : portfolio.getPositions()){
            // critical //
            if (pos.getSecurity().getIssuerId() == null) {
                result.add(new ServiceResultItem(portfolio, pos.getSecurity(),"security is missing an issuer."));
                result.setSuccess(false); // critical issue
            }
            if (pos.getSecurity().getBCUSIP() == null && pos.getSecurity().getISIN() == null) {
                result.add(new ServiceResultItem(portfolio, pos.getSecurity(),"security is critical identifiers."));
                result.setSuccess(false); // critical issue
            }

            // non-critical //
            if (pos.getSecurity().getISIN() == null) {
                result.add(new ServiceResultItem(portfolio, pos.getSecurity(),"security is missing an ISIN."));
            }
            if (pos.getSecurity().getBCUSIP() == null) {
                result.add(new ServiceResultItem(portfolio, pos.getSecurity(),"security is missing an BCUSIP."));
            }
        }
    }
}
