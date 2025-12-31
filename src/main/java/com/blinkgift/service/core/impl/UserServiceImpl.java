package com.blinkgift.service.core.impl;

import com.blinkgift.domain.entity.UserEntity;
import com.blinkgift.repository.UserFormRepository;
import com.blinkgift.repository.UserRepository;
import com.blinkgift.service.core.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserFormRepository userFormRepository;

    public UserEntity insertNewUser(Message message) {
        if (!userRepository.existsByTelegramId(message.getChat().getId())) {
            UserEntity userEntity = new UserEntity();
            userEntity.setTelegramId(message.getChat().getId());
            userRepository.save(userEntity);
        }
        return (UserEntity) userRepository.findByTelegramId(message.getChat().getId())
                .orElseThrow(() -> new IllegalStateException("User exists but not found"));
    }

    public void save(UserEntity user) {
        userRepository.save(user);
    }
}