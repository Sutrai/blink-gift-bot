package com.blinkgift.service.core;


import com.blinkgift.domain.entity.UserForm;

public interface UserFormService {

    UserForm findByUserId(Long userId);

    void save(UserForm userForm);

    UserForm findActiveFormByUserId(Long userId);

    UserForm findById(Long id);
}
