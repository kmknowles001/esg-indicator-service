package com.example.esgindicatorservice.data;

import com.example.esgindicatorservice.collection.EsgSignalCollection;
import com.example.esgindicatorservice.collection.PositionCollection;
import com.example.esgindicatorservice.entity.*;
import java.util.ArrayList;
import java.util.List;

//
//  simulate data access layer i.e. database / api calls
//
public class EsgIndicatorRepo {

    //
    // get positions from Position Service (Mockup).
    //
    public static PositionCollection getPositions(String portfolioId, String asOfDate){
        PositionCollection resultsFromPositionService = new PositionCollection();
        if (!isPortfolioInLookthroughSource()){
            resultsFromPositionService = getPositionDataviaAPI(portfolioId, asOfDate);
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
    // simulate Position Look-Through file
    //
    private static PositionCollection getPositionDataViaLookthroughFile(String portfolioId, String asOfDate){
        PositionCollection resultsFromPositionService = getPositionDataviaAPI(portfolioId, asOfDate);
        // add an additional position to simulate lookthrough.
        resultsFromPositionService.add(
                new Position(portfolioId, asOfDate,150.0,2.4556, new Security("EQUITY","BMW","005-BMW","DE498768462","BBMW43578H"))
        );
        resultsFromPositionService.setPositionSource("lookthrough");
        return resultsFromPositionService;
    }

    //
    // Simulate Position Service API call
    //
    private static PositionCollection getPositionDataviaAPI(String portfolioId, String asOfDate){

        PositionCollection resultsFromPositionService = new PositionCollection();
        resultsFromPositionService.setPositionSource("position-daas");

        resultsFromPositionService.add(
                new Position(portfolioId, asOfDate,150.0,2.4556, new Security("EQUITY","IBM","001-IBM","US87634789","BCIBMISF7"))
        );

        resultsFromPositionService.add(
                new Position(portfolioId, asOfDate,20.0,10.833, new Security("EQUITY","TESLA","002-TESLA","US3583455","BTESOSOJD45"))
        );

        resultsFromPositionService.add(
                new Position(portfolioId, asOfDate,65.0,7.2184, new Security("DERIVATIVE","ITRAXX 5Y SWAP","003-BARCLAYS","","BITRAXBAR"))
        );

        resultsFromPositionService.add(
                new Position(portfolioId, asOfDate,200.0,5.532, new Security("EQUITY","APPLE","004-APPLE","US12234358",""))
        );

        return resultsFromPositionService;
    }

    //
    // get list of ESG/PAI indicators
    //
    public static List<BaseEsgIndicator> getESGIndicators(){

        List<BaseEsgIndicator> returnList = new ArrayList<>();

        GhgIndicator pai1 = new GhgIndicator();
        returnList.add(pai1);

        CarbonIndicator pai2 = new CarbonIndicator();
        returnList.add(pai2);

        return returnList;
    }

    //
    // Simulate esg signals API call
    //
    public static EsgSignalCollection getIssuerSignals(){
        EsgSignalCollection esgSignalCollection = new EsgSignalCollection();
        esgSignalCollection.add((new EsgSignal("001-IBM",0.0234,0.0352,0.2124,0.1685)));
        esgSignalCollection.add((new EsgSignal("002-TESLA",0.0174,0.0174,0.358,0.0945)));
        esgSignalCollection.add((new EsgSignal("003-BARCLAYS",0.0755,0.0442,0.8112,0.2998)));
        esgSignalCollection.add((new EsgSignal("004-APPLE",0.0122,0.0255,0.2773,0.1129)));
        return esgSignalCollection;
    }
}