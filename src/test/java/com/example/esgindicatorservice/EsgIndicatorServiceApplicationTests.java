package com.example.esgindicatorservice;

import com.example.esgindicatorservice.service.EsgIndicatorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EsgIndicatorServiceApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    void testRun() {

        String portfolioId = "001_34352";

        EsgIndicatorService service = new EsgIndicatorService();

        service.calculateEsgIndicators(portfolioId, true);

    }
}
