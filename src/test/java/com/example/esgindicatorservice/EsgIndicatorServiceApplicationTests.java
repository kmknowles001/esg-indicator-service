package com.example.esgindicatorservice;

import com.example.esgindicatorservice.common.GsonUtil;
import com.example.esgindicatorservice.entity.Portfolio;
import com.example.esgindicatorservice.service.EsgIndicatorService;
import com.example.esgindicatorservice.service.ServiceResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EsgIndicatorServiceApplicationTests {


    @Test
    void jsonObjectTest(){

        String portfolioId = "001_34352";
        String asOfDate = "25-FEB-2024";

        Gson gson = new Gson();



        EsgIndicatorService service = new EsgIndicatorService();
        Portfolio portfolio = service.initialisePortfolio(portfolioId, asOfDate, null);

        GsonBuilder gsb = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting();
        JsonObject jo = gson.toJsonTree(portfolio).getAsJsonObject();





        String portId = portfolio.getPortfolioId();






    }

    @Test
    void simulateEsgIndicatorEndpoint() {

        try {
            String portfolioId = "001_34352";
            String asOfDate = "25-FEB-2024";

            EsgIndicatorService service = new EsgIndicatorService();
            ServiceResult result = service.calculateEsgIndicators(portfolioId, asOfDate, true);

            assert(result.isSuccess());
        }catch (Exception e){
            assert(false);
        }
    }
}
