package com.example.esgindicatorservice.service;

import com.example.esgindicatorservice.data.EsgIndicatorRepo;
import com.example.esgindicatorservice.entity.*;

import java.util.ArrayList;
import java.util.List;

public class EsgIndicatorService {

    //
    // declarations
    //
    private List<ESGIndicator> indicatorList = new ArrayList<>();

    //
    //  methods
    //
    public ServiceResult calculateEsgIndicators(String portfolioId, boolean debug){

        ServiceResult result = new ServiceResult(portfolioId);

        //
        // get portfolio & get positions
        //
        Portfolio portfolio = new Portfolio(portfolioId);
        portfolio.setPositions(EsgIndicatorRepo.getPositions(portfolio.getPortfolioId()));

        result.add(new ServiceResultItem(portfolio,"positionSource:" + portfolio.getPositionCollection().isLookThrough()));
        result.add(new ServiceResultItem(portfolio,"positionCount:" + portfolio.getPositionCollection().PositionCount()));
        result.setRecords(portfolio.getPositionCollection().PositionCount());

        //
        // get principle adverse indicators to calculate.
        //
        indicatorList = EsgIndicatorRepo.getESGIndicators();
        result.add(new ServiceResultItem(portfolio,"no of esg indicators to process:" + indicatorList.size()));

        //
        // check core data
        //
        if (validatePositionAndSecurityData(portfolio, result) == true){



        }
        return result;
    }



    //
    // validate data position & security data - before we start.
    //
    public boolean validatePositionAndSecurityData(Portfolio portfolio, ServiceResult result){

        for(Position pos : portfolio.getPositions()){

            // critical
            if (pos.getSecurity().getIssuerId() == null) {
                result.add(new ServiceResultItem(portfolio, pos.getSecurity(),"security is missing an issuer."));
                result.setSuccess(false); // critical issue
            }

            if (pos.getSecurity().getBCUSIP() == null && pos.getSecurity().getISIN() == null) {
                result.add(new ServiceResultItem(portfolio, pos.getSecurity(),"security is critical identifiers."));
                result.setSuccess(false); // critical issue
            }

            // non-critical
            if (pos.getSecurity().getISIN() == null) {
                result.add(new ServiceResultItem(portfolio, pos.getSecurity(),"security is missing an ISIN."));
            }

            if (pos.getSecurity().getBCUSIP() == null) {
                result.add(new ServiceResultItem(portfolio, pos.getSecurity(),"security is missing an BCUSIP."));
            }
        }
        return result.getSuccess();
    }


}
