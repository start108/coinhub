package com.jy.coinhub.service.impl;

import com.jy.coinhub.feign.BithumbFeignClient;
import com.jy.coinhub.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BithumbMarketServiceImpl implements MarketService {

    private final BithumbFeignClient bithumbFeignClient;

    private static final String korea = "_KRW";

    public double getCoinCurrentPrice(String coin) {
        return Double.parseDouble(
                bithumbFeignClient.getCoinPrice(coin.toUpperCase() + korea)
                .getData()
                .getClosing_price());
    }

    public List<String> getCoins() {
        return List.of("A", "B", "C");
    }
}
