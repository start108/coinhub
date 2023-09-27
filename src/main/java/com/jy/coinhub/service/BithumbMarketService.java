package com.jy.coinhub.service;

import com.jy.coinhub.dto.CoinBuyDTO;
import com.jy.coinhub.dto.CoinSellDTO;
import com.jy.coinhub.feign.BithumbFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BithumbMarketService implements MarketService {

    private final BithumbFeignClient bithumbFeignClient;

    private static final String korea = "_KRW";

    public double getCoinCurrentPrice(String coin) {
        return Double.parseDouble(
                bithumbFeignClient.getCoinPrice(coin.toUpperCase() + korea)
                .getData()
                .getClosing_price());
    }

    public List<String> getCoins() {

        List<String> resultList = new ArrayList<>();

        bithumbFeignClient.getAssetStatus().getData().forEach((k, v)->{
            if(v.getDeposit_status() == 1 && v.getWithdrawal_status() == 1) {
                resultList.add(k.toUpperCase());
            }
        });

        return resultList;
    }

    public CoinBuyDTO calculateBuy(List<String> commonCoins, double amount) {

        Map<String, Object> bithumbResponse = bithumbFeignClient.getOrderBook().getData();

        bithumbResponse.forEach((key, value) -> {

            if(!("timestamp".equalsIgnoreCase(key) || "payment_currency".equalsIgnoreCase(key))) {

                double availableCurrency = amount;
                double availableCoin = 0;

                String coin = key;
                List<Map<String, String>> wannaSell = (List<Map<String, String>>)((Map<String, Object>) value).get("asks");

                for(int i = 0; i < wannaSell.size(); i++) {

                    Double price = Double.parseDouble(wannaSell.get(i).get("price"));
                    Double quantity = Double.parseDouble(wannaSell.get(i).get("quantity"));
                    Double eachTotalPrice = price * quantity;
                    Double purchaseableCoinAmount = availableCurrency / price;

//                    if( ) {
//
//                    }
                }
            }
        });

        return null;
    }

    public CoinSellDTO calculateSell(CoinBuyDTO coinBuyDTO) {
        return null;
    }
}
