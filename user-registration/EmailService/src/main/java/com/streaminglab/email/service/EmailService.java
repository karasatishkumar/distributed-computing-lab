package com.streaminglab.email.service;

import com.streaminglab.email.entity.dto.UserDTO;

public interface EmailService {

    void sendSimpleMessage(UserDTO input);
}
