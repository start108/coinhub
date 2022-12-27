package com.jy.coinhub.service.impl;

import com.jy.coinhub.feign.UpbitFeignClient;
import com.jy.coinhub.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UpbitMarketServiceImpl implements MarketService {

    private final UpbitFeignClient upbitFeignClient;

    private static final String korea = "KRW-";

    public double getCoinCurrentPrice(String coin) {
        return upbitFeignClient.getCoinPrice(korea + coin.toLowerCase())
                .get(0)
                .getTrade_price();
    }

    public List<String> getCoins() {

        List<String> resultList = new ArrayList<>();

        upbitFeignClient.getMarketCode().forEach(x -> {
            if(x.getMarket().startsWith("KRW")) {
                resultList.add(x.getMarket().substring(4).toUpperCase());
            }
        });

        return resultList;
    }
}
