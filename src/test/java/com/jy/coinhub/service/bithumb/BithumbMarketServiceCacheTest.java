package com.jy.coinhub.service.bithumb;

import com.jy.coinhub.constant.CacheConstants;
import com.jy.coinhub.feign.BithumbFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${cache.ttl.default}")
    private String defaultTtl;

    @Value("${cache.ttl.withdrawlFee}")
    private String withdrawlFeeTtl;

    @BeforeEach
    void setUp() {

        cacheManager.getCache(CacheConstants.BITHUMB_COIN_PRICE).clear();
        cacheManager.getCache(CacheConstants.BITHUMB_ASSET_STATUS).clear();
        cacheManager.getCache(CacheConstants.BITHUMB_ORDER_BOOK).clear();
        cacheManager.getCache(CacheConstants.BITHUMB_WITHDRAWL_FEE).clear();
    }

    @Test
    void getCoinPriceTest() {

        String parameter = "BTC";
        assertNull(cacheManager.getCache(CacheConstants.BITHUMB_COIN_PRICE).get(parameter));

        bithumbFeignClient.getCoinPrice(parameter);

        assertNotNull(cacheManager.getCache(CacheConstants.BITHUMB_COIN_PRICE).get(parameter));
    }

    @Test
    void getCoinPriceTimeTest() throws Exception {

        String parameter = "BTC";
        assertNull(cacheManager.getCache(CacheConstants.BITHUMB_COIN_PRICE).get(parameter));

        bithumbFeignClient.getCoinPrice(parameter);

        assertNotNull(cacheManager.getCache(CacheConstants.BITHUMB_COIN_PRICE).get(parameter));

        Thread.sleep(Long.parseLong(defaultTtl));

        assertNull(cacheManager.getCache(CacheConstants.BITHUMB_COIN_PRICE).get(parameter));
    }
}