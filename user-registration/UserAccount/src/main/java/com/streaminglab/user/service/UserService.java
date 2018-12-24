package com.streaminglab.user.service;

import com.streaminglab.user.entity.User;

public interface UserService {

    User registerUser(User input);

    Iterable<User> findAll();
}
