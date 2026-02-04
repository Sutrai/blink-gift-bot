// src/main/java/com/blinkgift/controller/NotificationController.java
package com.blinkgift.controller;

import com.blinkgift.dto.SniperNotificationDto;
import com.blinkgift.service.bot.MessageService;
import com.blinkgift.telegram.Bot;
import com.blinkgift.util.KeyBoardUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/internal/v1")
@RequiredArgsConstructor
public class NotificationController {

    private final Bot bot;
    private final MessageService messageService;

    @PostMapping("/sniper-notify")
    public void sendSniperNotification(@RequestBody SniperNotificationDto dto) {
        log.info("Получен запрос на уведомление пользователя {}: {}", dto.getUserId(), dto.getGiftName());

        try {
            // Формируем текст сообщения
            String text = String.format(
                    "🎯 *Sniper Feed: Найден подарок!*\n\n" +
                            "🎁 *%s*\n" +
                            "👤 Модель: %s\n" +
                            "💰 Цена: `%s TON`\n" +
                            "🔥 Профит: `+%d%%`\n\n" +
                            "🏪 Маркетплейс: %s",
                    dto.getGiftName(),
                    dto.getModel(),
                    dto.getPrice(),
                    dto.getDealScore(),
                    dto.getMarketplace()
            );

            bot.sendMessage(messageService.executeMessage(
                    text,
                    dto.getUserId(),
                    KeyBoardUtils.createOpenAppButton()
            ));

        } catch (Exception e) {
            log.error("Ошибка при отправке сообщения пользователю {}: {}", dto.getUserId(), e.getMessage());
        }
    }
}