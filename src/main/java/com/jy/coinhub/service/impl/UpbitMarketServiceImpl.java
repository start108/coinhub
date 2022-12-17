package com.jy.coinhub.service.impl;

import com.jy.coinhub.feign.UpbitFeignClient;
import com.jy.coinhub.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpbitMarketServiceImpl implements MarketService {

    @Autowired
    UpbitFeignClient upbitFeignClient;

    private static final String korea = "KRW-";

    public double getCoinCurrentPrice(String coin) {
        return upbitFeignClient.getCoinPrice(korea + coin.toLowerCase())
                .get(0)
                .getTrade_price();
    }
}
