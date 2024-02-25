package com.example.esgindicatorservice.data;

import com.example.esgindicatorservice.entity.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

//
//  simulate data access layer i.e. database / api calls
//
public class EsgIndicatorRepo {

    //
    // get positions from Position Service (Mockup).
    //
    public static PositionCollection getPositions(String portfolioId){
        PositionCollection resultsFromPositionService = new PositionCollection();
        if (!isPortfolioInLookthroughSource()){
            resultsFromPositionService = getPositionDataviaAPI(portfolioId);
        }
        return resultsFromPositionService;
    }

    //
    // simulate check portfolio source i.e. position daas or tempory look-through file.
    //
    public static boolean isPortfolioInLookthroughSource(){
        return false;
    }

    //
    // create dummy positions (simulating Position Look-Through file)
    //
    private static PositionCollection getPositionDataViaLookthroughFile(String portfolioId){
        PositionCollection resultsFromPositionService = getPositionDataviaAPI(portfolioId);
        // add an additional position to simulate lookthrough.
        resultsFromPositionService.add(
                new Position(portfolioId, "25-FEB-2024",150.0,2.4556, new Security("EQUITY","BMW","005-BMW","DE498768462","BBMW43578H"))
        );
        resultsFromPositionService.setLookThrough(true);
        return resultsFromPositionService;
    }

    //
    // create dummy positions (simulating Postion Service API call)
    //
    private static PositionCollection getPositionDataviaAPI(String portfolioId){

        PositionCollection resultsFromPositionService = new PositionCollection();
        resultsFromPositionService.setLookThrough(false);

        resultsFromPositionService.add(
                new Position(portfolioId, "25-FEB-2024",150.0,2.4556, new Security("EQUITY","IBM","001-IBM","US87634789","BCIBMISF7"))
        );

        resultsFromPositionService.add(
                new Position(portfolioId, "25-FEB-2024",20.0,10.833, new Security("EQUITY","TESLA","002-TESLA","US3583455","BTESOSOJD45"))
        );

        resultsFromPositionService.add(
                new Position(portfolioId, "25-FEB-2024",65.0,7.2184, new Security("DERIVATIVE","ITRAXX 5Y SWAP","003-BARCLAYS","","BITRAXBAR"))
        );

        resultsFromPositionService.add(
                new Position(portfolioId, "25-FEB-2024",200.0,5.532, new Security("EQUITY","APPLE","004-APPLE","US12234358","BAPP45KFS"))
        );

        return resultsFromPositionService;
    }



    //
    // get list of ESG/PAI indicators
    //
    public static List<ESGIndicator> getESGIndicators(){

        List<ESGIndicator> returnList = new ArrayList<>();

        GHGIndicator pai1 = new GHGIndicator();
        returnList.add(pai1);

        CarbonIndicator pai2 = new CarbonIndicator();
        returnList.add(pai2);

        return returnList;
    }
}


