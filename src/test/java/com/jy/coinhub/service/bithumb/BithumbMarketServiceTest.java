package com.jy.coinhub.service.bithumb;

import com.jy.coinhub.dto.CoinBuyDTO;
import com.jy.coinhub.feign.BithumbFeignClient;
import com.jy.coinhub.feign.response.BithumbResponse;
import com.jy.coinhub.service.BithumbMarketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BithumbMarketServiceTest {

    @Mock
    private BithumbMarketService bithumbMarketService;

    @Mock
    private BithumbFeignClient bithumbFeignClient;

    @BeforeEach
    void setUp() {
        bithumbMarketService = new BithumbMarketService(bithumbFeignClient);
    }

    @Test
    void getCoinCurrentPrice() {
    }

    @Test
    void getCoins() {
    }

    @Test
    void calculateBuy() {

        List<String> commonCoin = List.of("A", "B", "C");
        BithumbResponse<Map<String, Object>> mockOrderBook = mockBithumbOrderBook();
        when(bithumbFeignClient.getOrderBook()).thenReturn(mockOrderBook);

        CoinBuyDTO coinBuyDTO = bithumbMarketService.calculateBuy(commonCoin, 5);

        assertEquals(1 + 1 + 0.5, coinBuyDTO.getAmounts().get("A"));
        assertEquals(1, coinBuyDTO.getOrderBooks().get("A").get(1D));
        assertEquals(1, coinBuyDTO.getOrderBooks().get("A").get(2D));
        assertEquals(0.5, coinBuyDTO.getOrderBooks().get("A").get(4D));

        assertEquals(2 + 1.5, coinBuyDTO.getAmounts().get("B"));
        assertEquals(2, coinBuyDTO.getOrderBooks().get("B").get(1D));
        assertEquals(1.5, coinBuyDTO.getOrderBooks().get("B").get(2D));

        assertEquals(3 + 1, coinBuyDTO.getAmounts().get("C"));
        assertEquals(3, coinBuyDTO.getOrderBooks().get("C").get(1D));
        assertEquals(1, coinBuyDTO.getOrderBooks().get("C").get(2D));
    }

    @Test
    void calculateSell() {
    }

    private BithumbResponse<Map<String, Object>> mockBithumbOrderBook() {

        BithumbResponse<Map<String, Object>> result = new BithumbResponse<>();

        result.setData(
                Map.of(
                        "A", Map.of(
                                "bids", new ArrayList<>(List.of(
                                        Map.of("price", "1", "quantity", "1"),
                                        Map.of("price", "2", "quantity", "1"),
                                        Map.of("price", "4", "quantity", "1")
                                )),
                                "asks", new ArrayList<>(List.of(
                                        Map.of("price", "4", "quantity", "1"),
                                        Map.of("price", "2", "quantity", "1"),
                                        Map.of("price", "1", "quantity", "1")
                                ))
                        ),
                        "B", Map.of(
                                "bids", new ArrayList<>(List.of(
                                        Map.of("price", "1", "quantity", "2"),
                                        Map.of("price", "2", "quantity", "2"),
                                        Map.of("price", "4", "quantity", "2")
                                )),
                                "asks", new ArrayList<>(List.of(
                                        Map.of("price", "4", "quantity", "2"),
                                        Map.of("price", "2", "quantity", "2"),
                                        Map.of("price", "1", "quantity", "2")
                                ))
                        ),
                        "C", Map.of(
                                "bids", new ArrayList<>(List.of(
                                        Map.of("price", "1", "quantity", "3"),
                                        Map.of("price", "2", "quantity", "3"),
                                        Map.of("price", "4", "quantity", "3")
                                )),
                                "asks", new ArrayList<>(List.of(
                                        Map.of("price", "4", "quantity", "3"),
                                        Map.of("price", "2", "quantity", "3"),
                                        Map.of("price", "1", "quantity", "3")
                                ))
                        )
                )
        );

        return result;
    }
}