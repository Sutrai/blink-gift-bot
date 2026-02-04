package com.blinkgift.service.handler;

import com.blinkgift.client.GiftDiscoveryClient;
import com.blinkgift.dto.GiftResponseDto;
import com.blinkgift.service.bot.MessageService;
import com.blinkgift.util.GiftUrlParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageHandler {

    private final MessageService messageService;
    private final GiftDiscoveryClient giftDiscoveryClient;

    public BotApiMethod<?> answer(Message message, com.blinkgift.telegram.Bot bot) {
        String text = message.getText();
        Long chatId = message.getChatId();
        String userName = message.getFrom().getUserName();

        log.info("Получено сообщение от @{}: {}", userName, text);

        if (text != null && text.contains("t.me/nft/")) {
            String giftName = GiftUrlParser.formatUrlToName(text);

            if (giftName != null) {
                try {
                    log.info("Отправка запроса в gift-discovery для подарка: {}", giftName);
                    GiftResponseDto gift = giftDiscoveryClient.getGiftByName(giftName);
                    log.info("Успешно получены данные от discovery для: {}. Оценка: {}",
                            gift.getName(),
                            (gift.getMarketData() != null ? gift.getMarketData().getEstimatedPrice() : "N/A"));
                    return messageService.executeMessage(
                            formatResponse(gift),
                            message.getChatId(),
                            null
                    );
                } catch (Exception e) {
                    log.error("Ошибка при запросе к gift-discovery для '{}': {}", giftName, e.getMessage());
                    return messageService.executeMessage(
                            "❌ Подарок *" + giftName + "* не найден в базе данных.",
                            message.getChatId(),
                            null
                    );
                }
            }
        }
        return null;
    }

    private String formatResponse(GiftResponseDto gift) {
        StringBuilder sb = new StringBuilder();
        sb.append("🎁 *").append(gift.getName()).append("*\n\n");

        if (gift.getAttributes() != null) {
            var at = gift.getAttributes();
            sb.append("📊 *Атрибуты:*\n");
            sb.append("• Model: ").append(at.getModel()).append(" (").append(at.getModelPrice()).append(" TON)\n");
            sb.append("• Backdrop: ").append(at.getBackdrop()).append(" (").append(at.getBackdropPrice()).append(" TON)\n");
            sb.append("• Symbol: ").append(at.getSymbol()).append("\n\n");
        }

        if (gift.getMarketData() != null) {
            var md = gift.getMarketData();
            sb.append("💰 *Оценка:* `").append(md.getEstimatedPrice()).append(" TON`\n");
            sb.append("📉 *Floor коллекции:* `").append(md.getCollectionFloorPrice()).append(" TON`\n");
        }

        return sb.toString();
    }
}