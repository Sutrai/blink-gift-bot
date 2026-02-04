package com.blinkgift.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class GiftResponseDto {
    private String name;
    private String collectionAddress;
    private Attributes attributes;
    private MarketData marketData;

    @Data
    public static class Attributes {
        private String model;
        private BigDecimal modelPrice;
        private String backdrop;
        private BigDecimal backdropPrice;
        private String symbol;
    }

    @Data
    public static class MarketData {
        private BigDecimal collectionFloorPrice;
        private BigDecimal estimatedPrice;
    }
}