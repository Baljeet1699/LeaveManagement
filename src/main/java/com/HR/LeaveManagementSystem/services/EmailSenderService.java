package com.HR.LeaveManagementSystem.services;

import java.io.File;

public interface EmailSenderService {

    void sendEmail(String to,String subject,String message);

    void sendEmail(String []to,String subject,String message);

    void sendEmailWithHtml(String to,String subject,String message);

    void sendEmailWithFile(String to, String subject, String message, File file);

}
