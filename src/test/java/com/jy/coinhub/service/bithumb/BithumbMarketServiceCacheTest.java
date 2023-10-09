package com.jy.coinhub.service.bithumb;

import com.jy.coinhub.feign.BithumbFeignClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

// @Disabled
@EnableCaching
@EnableFeignClients
@SpringBootTest
class BithumbMarketServiceCacheTest {

    @Autowired
    private BithumbFeignClient bithumbFeignClient;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void getCoinPriceTest() {

        String parameter = "BTC";
        assertNull(cacheManager.getCache("BITHUMB_COIN_PRICE").get(parameter));

        bithumbFeignClient.getCoinPrice(parameter);

        assertNotNull(cacheManager.getCache("BITHUMB_COIN_PRICE").get(parameter));
    }
}