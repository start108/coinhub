package com.jy.coinhub.service.bithumb;

import com.jy.coinhub.service.BithumbMarketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;

// @Disabled
@EnableFeignClients
@SpringBootTest
class BithumbMarketServiceIntegrationTest {

    @Autowired
    private BithumbMarketService bithumbMarketService;

    @Test
    void calculateFeeTest() throws Exception {

        Map<String, Double> result = bithumbMarketService.calculateFee();
        assertFalse(result.isEmpty());
    }
}