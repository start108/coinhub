package com.jy.coinhub.service;

import com.jy.coinhub.dto.CoinBuyDTO;
import com.jy.coinhub.dto.CoinSellDTO;
import com.jy.coinhub.dto.TransferCalculateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransferCalculateService {

    private final CommonMarketService commonMarketService;

    private final Map<String, MarketService> marketServices;

    public List<TransferCalculateDTO> calculate(String fromMarket, String toMarket, double amount) throws Exception{

        // 두개의 마켓의 공통 코인 추출
        List<String> commonCoins = commonMarketService.getCommonCoin(fromMarket, toMarket);

        MarketService fromMarketService = CommonMarketService.getMarketService(marketServices, fromMarket);

        MarketService toMarketService = CommonMarketService.getMarketService(marketServices, toMarket);

        // 얼마에 살 수 있고(fromMarket)
        CoinBuyDTO fromMarketBuyDto = fromMarketService.calculateBuy(commonCoins, amount);

        // 수수료 계산 (fromMarket)
        Map<String /* Coin Name */, Double /* Withdrawal Fee */> fromMarketTransferFee = fromMarketService.calculateFee();

        Map<String /* Coin Name */, Double /* Withdrawal Fee */> amountAfterFee = fromMarketBuyDto.afterTransferFee(fromMarketTransferFee);

        // 얼마에 팔 수 있는지(toMarket)
        CoinSellDTO toMarketSellDto = toMarketService.calculateSell(amountAfterFee);

        // 가장 높은 값을 받을 수 있는 코인을 선택
        List<String> topTenCoins = toMarketSellDto.getAmounts()
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey).toList();

        List<TransferCalculateDTO> resultList = new ArrayList<>();

        topTenCoins.forEach(coin -> resultList.add(
                new TransferCalculateDTO(
                        coin,
                        fromMarketBuyDto.getAmounts().get(coin),
                        fromMarketTransferFee.get(coin),
                        toMarketSellDto.getAmounts().get(coin),
                        fromMarketBuyDto.getOrderBooks().get(coin),
                        toMarketSellDto.getOrderBooks().get(coin)
        )));

        return resultList;
    }
}
