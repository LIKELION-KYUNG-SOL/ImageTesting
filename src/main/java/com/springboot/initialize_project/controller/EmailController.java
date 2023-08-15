package com.springboot.initialize_project.controller;

import com.springboot.initialize_project.data.dto.mail.EmailDto;
import com.springboot.initialize_project.data.dto.mail.ResponseVerificationMailDto;
import com.springboot.initialize_project.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/email")
public class EmailController {
    MailSenderService mailSenderService;
    @Autowired
    public EmailController(MailSenderService mailSenderService){
        this.mailSenderService = mailSenderService;
    }

    @PostMapping()
    public void sendMail(HttpServletRequest servletRequest,
                         HttpServletResponse servletResponse,
                         @RequestBody EmailDto emailDto) throws MessagingException {
        mailSenderService.sendAcceptMailForOne(emailDto);
    }

    @PostMapping("/verification")
    public ResponseEntity<ResponseVerificationMailDto> sendVerificationMail(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            @RequestBody EmailDto emailDto) throws MessagingException {

        ResponseVerificationMailDto responseVerificationMailDto = mailSenderService.sendVerificationMail(emailDto);

        return ResponseEntity.status(HttpStatus.OK).body(responseVerificationMailDto);
    }

}
