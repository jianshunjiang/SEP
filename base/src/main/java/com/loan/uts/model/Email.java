package com.loan.uts.model;

/**
 * The email template.
 */
public class Email {

    public static final String ASSIGNED = "assigned to you.";

    private String[] recipients;
    private String subject;
    private String content;
    private String type;

    public Email(){}

    public Email(Application application, Student student){
        setRecipient(student.getEmail());
        setContent(application.getTitle(),student.getFirstname() + " " +student.getLastname(),
                application.getId(), application.getStatus());
        setSubject("Application Notification");
        type = application.getStatus();
    }

    public Email(Application application, Manager manager){
        setRecipient(manager.getEmail());
        setContent(application.getTitle(),manager.getFirstname() + " " +manager.getLastname(),
                application.getId(), ASSIGNED);
        setSubject("Application Notification");
        type = ASSIGNED;
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

    public void setContent(String title, String name, int id, String messageType) {
        this.content = "<html><body>Dear " + name +", <br/><br/>";
        this.content +="Application No. " + id + " " + title +" is " + messageType;
        this.content +="<br/>For further information, check uts loan system.";

        this.content +="<br/><br/>Best Regards, <br/>UTS Loan System" +
                "</body></html>";
    }

    @Override
    public String toString() {
        return "Recipient: " + recipients[0] + "; Type: " + type;
    }
}
