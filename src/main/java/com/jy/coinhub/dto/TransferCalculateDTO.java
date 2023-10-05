package com.jy.coinhub.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class TransferCalculateDTO {
    private String coin;
    private double buyAmount;
    private double fee;
    private double sellAmount;
    private Map<Double, Double> buyOrderBook;
    private Map<Double, Double> sellOrderBook;
}
