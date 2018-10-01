package com.loan.uts.service;

import com.loan.uts.model.Application;
import com.loan.uts.model.Email;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static com.loan.uts.model.Application.ACCEPTED;

/**
 * Service for sending emails.
 * @Author Tong Lei
 */
@Service
public class EmailService {
    @Resource
    private TaskExecutor taskExecutor;
    @Resource
    private JavaMailSender javaMailSender;

    /**
     * Notify students the status of the application has changed by email
     * @param application
     */
    public void notifyStudent(Application application){
        Email email = new Email(application, application.getStudent());
        sendEmail(email);
    }

    /**
     * Notify manager that an application is assigned to him/her by email.
     * @param application
     */
    public void notifyManager(Application application){
        Email email = new Email(application, application.getManager());
        sendEmail(email);
    }

    private void sendEmail(Email email){
        final MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    true, "utf-8");
            helper.setFrom("uts.loan.system.2018@gmail.com");
            helper.setTo(email.getRecipients());
            helper.setSubject( email.getSubject() );
            helper.setText(email.getContent(), true);
            taskExecutor.execute(new Runnable() {
                public void run() {
                    javaMailSender.send(message);
                }
            });
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
