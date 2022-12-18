package com.jy.coinhub.service.impl;

import com.jy.coinhub.feign.BithumbFeignClient;
import com.jy.coinhub.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BithumbMarketServiceImpl implements MarketService {

    @Autowired
    BithumbFeignClient bithumbFeignClient;

    private static final String korea = "_KRW";

    public double getCoinCurrentPrice(String coin) {
        return Double.parseDouble(
                bithumbFeignClient.getCoinPrice(coin.toUpperCase() + korea)
                .getData()
                .getClosing_price());
    }
}
