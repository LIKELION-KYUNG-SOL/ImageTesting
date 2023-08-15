package com.springboot.initialize_project.service.impl;

import com.springboot.initialize_project.data.dto.mail.EmailDto;
import com.springboot.initialize_project.data.dto.mail.ResponseVerificationMailDto;
import com.springboot.initialize_project.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Random;

@Service
public class MailSenderServiceImpl implements MailSenderService {
    private final JavaMailSender javaMailSender;
    private SpringTemplateEngine templateEngine;

    @Autowired
    public MailSenderServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine){
        this.javaMailSender = javaMailSender;
        this.templateEngine = springTemplateEngine;
    }

    @Override
    public void sendAcceptMail(List<EmailDto> acceptEmailDto) throws MessagingException {

    }

    @Override
    public ResponseVerificationMailDto sendVerificationMail(EmailDto acceptEmailDto) throws MessagingException {

        ResponseVerificationMailDto responseVerificationMailDto = new ResponseVerificationMailDto();
        Integer verificationNumber = getVerificationNumber();

        responseVerificationMailDto.setVerificationNumber(verificationNumber);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(acceptEmailDto.getReceiverEmail());
        helper.setSubject("강남대학교 멋쟁이사자처럼 서류전형 결과 공지");

        //템플릿에 전달할 데이터 설정
        Context context = new Context();
        context.setVariable("name", verificationNumber);

        //메일 내용 설정 : 템플릿 프로세스
        String html = templateEngine.process("acceptEmail.html",context);
        helper.setText(html, true);

        javaMailSender.send(message);

        return responseVerificationMailDto;
    }

    @Override
    public void sendAcceptMailForOne(EmailDto acceptEmailDto) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(acceptEmailDto.getReceiverEmail());
        helper.setSubject("강남대학교 멋쟁이사자처럼 서류전형 결과 공지");

        //템플릿에 전달할 데이터 설정
        Context context = new Context();
        context.setVariable("name", acceptEmailDto.getReceiverName());

        //메일 내용 설정 : 템플릿 프로세스
        String html = templateEngine.process("acceptEmail.html",context);
        helper.setText(html, true);

        //helper.addInline("image1", new ClassPathResource("templates/images/_.png"));
        //helper.addInline("image2", new ClassPathResource("templates/images/.jpg"));

        //메일 보내기
        javaMailSender.send(message);
    }

    public Integer getVerificationNumber() {
        // 난수의 범위 111111 ~ 999999 (6자리 난수)
        Random r = new Random();
        Integer checkNum = r.nextInt(888888) + 111111;
        //System.out.println("인증번호 : " + checkNum);

        return checkNum;
    }

}
