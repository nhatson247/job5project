package com.fpt.job5project.service;

import com.fpt.job5project.dto.MailDTO;

import jakarta.mail.MessagingException;

public interface IMailService {
    void sendHtmlMail(MailDTO mailDTO, String templateName) throws MessagingException;
}
