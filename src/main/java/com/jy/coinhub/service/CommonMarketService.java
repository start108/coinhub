package com.jy.coinhub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CommonMarketService {

    private final Map<String, MarketService> marketServices;

    public double getPrice(String market, String coin) {

        MarketService marketService = getMarketService(marketServices, market);

        return marketService.getCoinCurrentPrice(coin);
    }

    public List<String> getCommonCoin(String fromMarket, String toMarket) {

        MarketService fromMarketService = getMarketService(marketServices, fromMarket);
        MarketService toMarketService = getMarketService(marketServices, toMarket);

        List<String> fromMarketCoins = fromMarketService.getCoins();
        List<String> toMarketCoins = toMarketService.getCoins();

        List<String> resultList = new ArrayList<>();

        for (String coin : fromMarketCoins) {
            if (toMarketCoins.contains(coin)) {
                resultList.add(coin);
            }
        }

        return resultList;
    }

    public static MarketService getMarketService(Map<String, MarketService> marketService, String market) {

        for (String key : marketService.keySet()) {
            if (key.substring(0, market.length()).equals(market.toLowerCase())) {
                return marketService.get(key);
            }
        }

        return null;
    }
}