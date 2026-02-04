// src/main/java/com/blinkgift/dto/SniperNotificationDto.java
package com.blinkgift.dto;

import lombok.Data;

@Data
public class SniperNotificationDto {
    private Long userId;       // Telegram ID пользователя
    private String giftName;   // Название (например, Plush Pepe #1)
    private String model;      // Модель
    private String price;      // Цена (TON)
    private Integer dealScore; // Процент профита
    private String marketplace;// Название маркета
}