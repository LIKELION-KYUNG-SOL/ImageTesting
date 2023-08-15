package com.springboot.initialize_project.data.dto.mail;

import lombok.Data;

@Data
public class EmailDto {
    String subject;
    String receiverName;
    String receiverEmail;
}
