package com.loan.uts.model;

/**
 * The email template.
 */
public class Email {


    private String[] recipients;
    private String subject;
    private String content;

    public Email(){}

    public Email(int applicationId, Student student, String messageType){
        setRecipient(student.getEmail());
        setContent(student.getFirstname() + " " +student.getLastname(),
                applicationId, messageType);
        setSubject("Application Notification");
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public void setRecipient(String recipient){
        this.recipients = new String[1];
        recipients[0] = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String name, int id, String messageType) {
        this.content = "<html><body>Dear " + name +", <br/><br/>";
        this.content +="Your application No. " + id + " is " + messageType;
        this.content +="<br/>For further information, check uts loan system.";

        this.content +="<br/><br/>Best Regards, <br/>UTS Loan System" +
                "</body></html>";
    }
}
