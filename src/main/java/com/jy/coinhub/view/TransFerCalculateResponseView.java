package com.jy.coinhub.view;

import com.jy.coinhub.dto.TransferCalculateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class TransFerCalculateResponseView {

    private String coin;
    private double amount;
    private Map<Double, Double> buyOrderBook;
    private Map<Double, Double> sellOrderBook;

    public static TransFerCalculateResponseView of(TransferCalculateDTO transferCalculateDTO) {

        return new TransFerCalculateResponseView(
                transferCalculateDTO.getCoin(),
                transferCalculateDTO.getAmount(),
                transferCalculateDTO.getBuyOrderBook(),
                transferCalculateDTO.getSellOrderBook()
        );
    }
}
