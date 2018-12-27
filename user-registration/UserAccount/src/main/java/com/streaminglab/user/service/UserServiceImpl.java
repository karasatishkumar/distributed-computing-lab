package com.streaminglab.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.streaminglab.user.entity.User;
import com.streaminglab.user.kafka.producer.Sender;
import com.streaminglab.user.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {

    @Value("${spring.kafka.topic.userRegistered}")
    private String USER_REGISTERED_TOPIC;

    private UserRepository userRepository;
    private Sender sender;

    @Autowired
    UserServiceImpl(UserRepository userRepository, Sender sender) {
        this.userRepository = userRepository;
        this.sender = sender;
    }

    @Override
    public User registerUser(User input) {
        User createdUser = userRepository.save(input);
        sender.send(USER_REGISTERED_TOPIC, createdUser);
        return createdUser;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }
}
