package com.jy.coinhub.service.impl;

import com.jy.coinhub.dto.CoinBuyDTO;
import com.jy.coinhub.dto.CoinSellDTO;
import com.jy.coinhub.dto.TransferCalculateDTO;
import com.jy.coinhub.service.CommonMarketService;
import com.jy.coinhub.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransferCalculateService {

    private final CommonMarketService commonMarketService;

    private final Map<String, MarketService> marketServices;

//    public TransferCalculateDTO calculate(String fromMarket, String toMarket, double amount) {
//
//        // 두개의 마켓의 공통 코인 추출
//        List<String> commonCoins = commonMarketService.getCommonCoin(fromMarket, toMarket);
//
//        MarketService fromMarketService = CommonMarketService.getCommonCoins(marketServices, fromMarket);
//
//        MarketService toMarketService = CommonMarketService.getCommonCoins(marketServices, toMarket);
//
//        // 얼마에 살 수 있고(fromMarket)
//        CoinBuyDTO fromMarketBuyDto = fromMarketService.calculateBuy(commonCoins, amount);
//
//        // 수수료 계산 (fromMarket)
//        Map<String, Double> fromMarketTransferFee = fromMarketService.calculateFee(commonCoins, amount);
//
//        // 얼마에 팔 수 있는지(toMarket)
//        CoinSellDTO toMarketSellDto = toMarketService.calculateSell(commonCoins, amount);
//
//        // 가장 높은 값을 받을 수 있는 코인을 선택
//        String transferCoin = toMarketSellDto.getAmounts().keySet().get(0); // TODO : 가장 많은 현금 받는 코인 선택
//
//        return new TransferCalculateDTO(
//                transferCoin,
//                toMarketSellDto.getAmounts().get(transferCoin),
//                fromMarketBuyDto.getOrderBooks().get(transferCoin),
//                toMarketSellDto.getOrderBooks().get(transferCoin)
//        );
//    }
}
