package com.jy.coinhub.configuration;

import com.jy.coinhub.constant.CacheConstants;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CacheEvictConfig {

    @CacheEvict(value = {CacheConstants.BITHUMB_COIN_PRICE, CacheConstants.BITHUMB_ORDER_BOOK, CacheConstants.UPBIT_COIN_PRICE}, allEntries = true)
    @Scheduled(fixedRateString = "${cache.ttl.default}")
    public void emptyDefaultCache() {
    }

    @CacheEvict(value = {CacheConstants.BITHUMB_WITHDRAWL_FEE, CacheConstants.UPBIT_WITHDRAWL_FEE, CacheConstants.BITHUMB_ASSET_STATUS, CacheConstants.UPBIT_MARKET_CODE}, allEntries = true)
    @Scheduled(fixedRateString = "${cache.ttl.withdrawlFee}")
    public void emptyWithdrawalFeeCache() {
    }
}
