package com.example.esgindicatorservice;

import com.example.esgindicatorservice.service.EsgIndicatorService;
import com.example.esgindicatorservice.service.ServiceResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EsgIndicatorServiceApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void simulateEsgIndicatorEndpoint() {

        try {
            String portfolioId = "001_34352";
            String asOfDate = "25-FEB-2024";

            EsgIndicatorService service = new EsgIndicatorService();
            ServiceResult result = service.calculateEsgIndicators(portfolioId, asOfDate, true);

            assert(result.getSuccess());
        }catch (Exception e){
            assert(false);
        }
    }
}
