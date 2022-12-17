package com.jy.coinhub.controlller;

import com.jy.coinhub.service.CommonMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketController {

    @Autowired
    private CommonMarketService commonMarketService;

    @GetMapping("/price")
    public double getPrice(@RequestParam String market, @RequestParam String coin) {
        return commonMarketService.getPrice(market, coin);
    }
}
