package com.jy.coinhub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

@Getter
@AllArgsConstructor
public class CoinBuyDTO {
    private Map<String, Double> amounts;
    private Map<String, SortedMap<Double, Double>> orderBooks;

    public Map<String /* Coin Name */, Double /* Amount */> afterTransferFee(Map<String /* Coin Name */, Double /* Withdrawal Fee */> fromMarketTransferFee) {

        Map<String, Double> resultMap = new HashMap<>();

        amounts.forEach((key, value) -> {
            if(fromMarketTransferFee.containsKey(key)) {
                resultMap.put(key, amounts.get(key) - fromMarketTransferFee.get(key));
            }
        });

        return resultMap;
    }
}
