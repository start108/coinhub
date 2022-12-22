package com.jy.coinhub.controlller;

import com.jy.coinhub.service.impl.TransferCalculateService;
import com.jy.coinhub.view.TransFerCalculateResponseView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TransferCalculateContorller {

    private final TransferCalculateService transferCalculateService;
    @GetMapping("/transfer-calculate")
    public TransFerCalculateResponseView getPrice(@RequestParam String fromMarket, @RequestParam String toMarket, @RequestParam double amount) {
        return null;
//        return TransFerCalculateResponseView.of(transferCalculateService.calculate(fromMarket, toMarket, amount));
    }
}
