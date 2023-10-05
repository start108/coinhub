package com.jy.coinhub.service;

import com.jy.coinhub.dto.CoinBuyDTO;
import com.jy.coinhub.dto.CoinSellDTO;
import com.jy.coinhub.feign.UpbitFeeFeignClient;
import com.jy.coinhub.feign.UpbitFeignClient;
import com.jy.coinhub.model.UpbitEachWithdrawlFee;
import com.jy.coinhub.model.UpbitWithdrawlFee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpbitMarketService implements MarketService {

    private final UpbitFeignClient upbitFeignClient;

    private final UpbitFeeFeignClient upbitFeeFeignClient;

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

    @Override
    public CoinBuyDTO calculateBuy(List<String> commonCoins, double amount) {
        return null;
    }

    @Override
    public CoinSellDTO calculateSell(CoinBuyDTO coinBuyDTO) {
        return null;
    }

    @Override
    public Map<String, Double> calculateFee() throws Exception {
        return upbitFeeFeignClient.getWithdrawlFee()
                .getData()
                .stream()
                .collect(Collectors.toMap(
                        UpbitEachWithdrawlFee::getCurrency, UpbitEachWithdrawlFee::getWithdrawFee
        ));
    }
}
