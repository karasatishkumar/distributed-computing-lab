package com.streaminglab.email.repository;

import org.springframework.data.repository.CrudRepository;

import com.streaminglab.email.entity.Mail;


public interface MailRepository extends CrudRepository<Mail, Long> {

}