package com.jy.coinhub.view;

import com.jy.coinhub.dto.TransferCalculateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class TransFerCalculateResponseView {

    private String coin;
    private double buyAmount;
    private double fee;
    private double sellAmount;
    private Map<Double, Double> buyOrderBook;
    private Map<Double, Double> sellOrderBook;
    private double profit;
    private double profitRatio;

    // DTO -> View
    public static TransFerCalculateResponseView of(TransferCalculateDTO transferCalculateDTO, double inputAmount) {

        return new TransFerCalculateResponseView(
                transferCalculateDTO.getCoin(),
                transferCalculateDTO.getBuyAmount(),
                transferCalculateDTO.getFee(),
                transferCalculateDTO.getSellAmount(),
                transferCalculateDTO.getBuyOrderBook(),
                transferCalculateDTO.getSellOrderBook(),
                transferCalculateDTO.getSellAmount() - inputAmount,
                transferCalculateDTO.getSellAmount() / inputAmount
        );
    }
}
