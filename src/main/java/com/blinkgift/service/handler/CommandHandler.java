package com.blinkgift.service.handler;

import com.blinkgift.data.CommandData;
import com.blinkgift.domain.entity.UserEntity;
import com.blinkgift.domain.entity.UserForm;
import com.blinkgift.repository.UserRepository;
import com.blinkgift.service.bot.MessageService;
import com.blinkgift.service.core.DataService;
import com.blinkgift.service.core.UserFormService;
import com.blinkgift.telegram.Bot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommandHandler {

    private final UserFormService userFormService;
    private final MessageService messageService;
    private final UserRepository userRepository;
    private final DataService dataService;

    public BotApiMethod<?> answer(Update update, Bot bot) {
        String command =  update.getMessage().getText().substring(1);
        Long userId = update.getMessage().getFrom().getId();
        String userName = update.getMessage().getFrom().getUserName();
        CommandData commandData;

        try {
            commandData = CommandData.valueOf(command);
        } catch (Exception e) {
            log.warn("Unsupported command was received: {}", command);
            return null; //TODO
        }

        switch (commandData) {
            case start -> {
                 UserEntity user = userRepository.save(dataService.insertNewUser(update.getMessage()));

                UserForm userForm = userFormService.findByUserId(userId);
                if (userForm == null) {
                    userForm = new UserForm();
                    userForm.setUsername(userName);
                    userForm.setUser(user);
                }
                return messageService.executeMessage(
                    "привет",
                        update.getMessage().getChatId(),
                        null
                );
            }

            default -> {
                log.warn("Unhandled command: {}", commandData);
                throw new UnsupportedOperationException();
            }
        }
    }
}