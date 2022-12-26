package com.jy.coinhub.controlller;

import com.jy.coinhub.service.CommonMarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MarketController {

    private final CommonMarketService commonMarketService;

    @GetMapping("/price")
    public double getPrice(@RequestParam String market, @RequestParam String coin) {
        return commonMarketService.getPrice(market, coin);
    }

    @GetMapping("/coins")
    public List<String> getCoins(@RequestParam String market) {

        return null;
//        CommonMarketService.getCommonCoins()
//        return commonMarketService.
    }
}
