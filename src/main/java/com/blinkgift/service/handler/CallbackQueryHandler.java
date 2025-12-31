package com.blinkgift.service.handler;

import com.blinkgift.data.ButtonData;
import com.blinkgift.domain.entity.UserForm;
import com.blinkgift.service.bot.MessageService;
import com.blinkgift.service.core.UserFormService;
import com.blinkgift.telegram.Bot;
import com.blinkgift.util.ClassUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Slf4j
@Service
@RequiredArgsConstructor
public class CallbackQueryHandler {

    private final UserFormService userFormService;
    private final MessageService messageService;

    public BotApiMethod<?> answer(CallbackQuery callbackQuery, Bot bot) {
        return null;

    }
}