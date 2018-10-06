package com.loan.uts.model;

import java.io.Serializable;

/**
 * The email template.
 */
public class Email implements Serializable {

    public static final String ASSIGNED = "assigned to you.";

    private String[] recipients;
    private String subject;
    private String content;
    private String type;

    /**
     * The constructor of Email.
     */
    public Email(){}

    /**
     * The constructor of Email.
     * @param application
     * @param student
     */

    public Email(Application application, Student student){
        setRecipient(student.getEmail());
        setContent(application.getTitle(),student.getFirstname() + " " +student.getLastname(),
                application.getId(), application.getStatus());
        setSubject("Application Notification");
        type = application.getStatus();
    }

    /**
     *
     * @param application
     * @param manager
     */
    public Email(Application application, Manager manager){
        setRecipient(manager.getEmail());
        setContent(application.getTitle(),manager.getFirstname() + " " +manager.getLastname(),
                application.getId(), ASSIGNED);
        setSubject("Application Notification");
        type = ASSIGNED;
    }

    // The getter/setter function of variable recipients.
    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    // The getter/setter function of variable recipient.
    public void setRecipient(String recipient){
        this.recipients = new String[1];
        recipients[0] = recipient;
    }

    // The getter/setter function of variable subject.
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    // The getter/setter function of variable content.
    public String getContent() {
        return content;
    }

    public void setContent(String title, String name, int id, String messageType) {
        this.content = "<html><body>Dear " + name +", <br/><br/>";
        this.content +="Application No. " + id + " " + title +" is " + messageType;
        this.content +="<br/>For further information, check uts loan system.";

        this.content +="<br/><br/>Best Regards, <br/>UTS Loan System" +
                "</body></html>";
    }

    /*
    Override toString function
    Return the string in the following format.
     */
    @Override
    public String toString() {
        return "Recipient: " + recipients[0] + "; Type: " + type;
    }
}
