package com.blinkgift.service.handler;

import com.blinkgift.domain.entity.UserEntity;
import com.blinkgift.domain.entity.UserForm;
import com.blinkgift.service.bot.MessageService;
import com.blinkgift.service.core.UserFormService;
import com.blinkgift.service.core.UserService;
import com.blinkgift.telegram.Bot;
import com.blinkgift.util.ClassUtils;
import com.blinkgift.util.KeyBoardUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageHandler {

    private final UserFormService userFormService;
    private final MessageService messageService;
    private final UserService userService;

    public BotApiMethod<?> answer(Message message, Bot bot) {

        return null;
    }
}
