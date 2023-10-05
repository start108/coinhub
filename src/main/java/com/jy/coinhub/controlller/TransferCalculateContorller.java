package com.jy.coinhub.controlller;

import com.jy.coinhub.service.TransferCalculateService;
import com.jy.coinhub.view.TransFerCalculateResponseView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransferCalculateContorller {

    private final TransferCalculateService transferCalculateService;

    @GetMapping("/transfer-calculate")
    public List<TransFerCalculateResponseView> getPrice(@RequestParam String fromMarket, @RequestParam String toMarket, @RequestParam double amount) throws Exception {
        return transferCalculateService.calculate(fromMarket, toMarket, amount)
                .stream()
                .map(key -> TransFerCalculateResponseView.of(key, amount))
                .toList();
    }
}
