package com.blinkgift.service.core.impl;

import com.blinkgift.domain.entity.UserEntity;
import com.blinkgift.service.core.DataService;
import com.blinkgift.service.core.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {

    private final UserService userService;

    @Override
    public UserEntity insertNewUser(Message message) {
        return userService.insertNewUser(message);
    }
}
