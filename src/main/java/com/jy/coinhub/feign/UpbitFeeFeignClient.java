package com.jy.coinhub.feign;

import com.jy.coinhub.constant.CacheConstants;
import com.jy.coinhub.model.UpbitCoinPrice;
import com.jy.coinhub.model.UpbitMarketCode;
import com.jy.coinhub.model.UpbitWithdrawlFee;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "upbitFee", url = "https://api-manager.upbit.com/api/v1/kv")
public interface UpbitFeeFeignClient {

    @Cacheable(CacheConstants.UPBIT_WITHDRAWL_FEE)
    @GetMapping("/UPBIT_PC_COIN_DEPOSIT_AND_WITHDRAW_GUIDE")
    UpbitWithdrawlFee getWithdrawlFee();
}
