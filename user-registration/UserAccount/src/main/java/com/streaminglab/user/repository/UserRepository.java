package com.streaminglab.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.streaminglab.user.entity.User;


public interface UserRepository extends CrudRepository<User, Long> {

}