package com.springboot.initialize_project.service;

import com.springboot.initialize_project.data.dto.mail.EmailDto;
import com.springboot.initialize_project.data.dto.mail.ResponseVerificationMailDto;

import javax.mail.MessagingException;
import java.util.List;

public interface MailSenderService {
    void sendAcceptMail(List<EmailDto> acceptEmailDto) throws MessagingException;
    ResponseVerificationMailDto sendVerificationMail(EmailDto acceptEmailDto) throws MessagingException;
    void sendAcceptMailForOne(EmailDto acceptEmailDto) throws MessagingException;
}
