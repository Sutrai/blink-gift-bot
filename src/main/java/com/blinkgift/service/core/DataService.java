package com.blinkgift.service.core;

import com.blinkgift.domain.entity.UserEntity;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface DataService {

    UserEntity insertNewUser(Message message);

}
