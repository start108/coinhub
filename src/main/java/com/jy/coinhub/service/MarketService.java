package com.jy.coinhub.service;

import com.jy.coinhub.dto.CoinBuyDTO;
import com.jy.coinhub.dto.CoinSellDTO;

import java.util.List;
import java.util.Map;

public interface MarketService {
    double getCoinCurrentPrice(String coin);

    List<String> getCoins();

    CoinBuyDTO calculateBuy(List<String> commonCoins, double amount);

    CoinSellDTO calculateSell(CoinBuyDTO coinBuyDTO);

    Map<String /* Coin Name */, Double /* Withdrawal Fee */> calculateFee() throws Exception;
}
