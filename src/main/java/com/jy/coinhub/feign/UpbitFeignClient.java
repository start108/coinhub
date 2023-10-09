package com.jy.coinhub.feign;

import com.jy.coinhub.model.UpbitCoinPrice;
import com.jy.coinhub.model.UpbitMarketCode;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "upbit", url = "https://api.upbit.com/v1")
public interface UpbitFeignClient {

    @Cacheable("UPBIT_COIN_PRICE")
    @GetMapping("/ticker")
    List<UpbitCoinPrice> getCoinPrice(@RequestParam("markets") String coin);

    @Cacheable("UPBIT_MARKET_CODE")
    @GetMapping("/market/all")
    List<UpbitMarketCode> getMarketCode();
}
