package com.HR.LeaveManagementSystem;

import com.HR.LeaveManagementSystem.services.EmailSenderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailSenderService emailSenderService;

    @Test
    void emailSendTest(){
        System.out.println("Sending email...");
        emailSenderService.sendEmail("malkiatsingh01@gmail.com","Email from Employee for Absence Request approval","Absence Request approval html");
    }

    @Test
    void emailSendHtmlTest(){
        System.out.println("Sending email...");

        String url = "http://localhost:5175/";

        String emailHtml = "" +
                "<h1 style='color:red'>Absence Request Approval html</h1>" +
                "<a href='"+url+"'>"+url+"</a>";
        emailSenderService.sendEmailWithHtml("baljeetpte1699@gmail.com","Email from Employee for Absence Request approval",emailHtml);
    }

    @Test
    void emailSendFileTest(){
        System.out.println("Sending email...");
        emailSenderService.sendEmailWithFile("baljeetpte1699@gmail.com","Email from Employee for Absence Request approval","Email with file",new File("C:/Users/immor/Desktop/LeaveManagementSystem/src/main/resources/static/excels/Reports-export.xlsx"));
    }

}

