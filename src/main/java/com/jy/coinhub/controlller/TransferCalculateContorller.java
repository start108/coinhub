package com.jy.coinhub.controlller;

import com.jy.coinhub.view.TransFerCalculateResponseView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TransferCalculateContorller {

    @GetMapping("/transfer-calculate")
    public TransFerCalculateResponseView getPrice(@RequestParam String fromMarket, @RequestParam String toMarket, @RequestParam double amount) {
        return new TransFerCalculateResponseView("BTC", 123.456, Map.of(123D, 123D), Map.of(123D, 123D));
    }
}
