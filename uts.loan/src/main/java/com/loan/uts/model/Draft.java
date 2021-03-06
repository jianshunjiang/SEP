package com.loan.uts.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;

/**
 * Define Draft class.
 */
@Entity
@Table(name = "draft", schema = "uts_loan", catalog = "")
public class Draft implements Serializable {
    private Integer id;
    private String content;
    private Integer studentId;
    private java.util.Date lastEdit;
    private Student student;
    private String title;
    private Collection<Attachment> attachments;
    private Double amount;
    private Double sum;
    private Integer payBackYears;

    /**
     * The constructor of Draft.
     */
    public Draft(){}

    /**
     * The constructor of Draft.
     * @param title
     * @param content
     * @param student
     */
    public Draft(String title, String content, Student student, Integer payBackYears, Double amount, Double sum){
        this.title = title;
        this.student = student;
        this.content = content;
        this.lastEdit = new java.util.Date();
        this.payBackYears = payBackYears;
        this.amount = amount;
        this.sum = sum;
    }

    // The getter/setter function of variable student.
    @OneToOne(mappedBy = "draft")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setLastEdit(java.util.Date lastEdit) {
        this.lastEdit = lastEdit;
    }

    // The getter/setter function of variable ID.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // The getter/setter function of variable content.
    @Basic
    @Column(name = "content", nullable = true, length = 5000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // The getter/setter function of variable lastEdit.
    @Basic
    @Column(name = "last_edit", nullable = true)
    public java.util.Date getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(Date lastEdit) {
        this.lastEdit = lastEdit;
    }

    /*
    Override equals function
    Judge the equation of two Administrator classes through all the variables above.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Draft draft = (Draft) o;

        if (id != null ? !id.equals(draft.id) : draft.id != null) return false;
        if (content != null ? !content.equals(draft.content) : draft.content != null) return false;
        if (studentId != null ? !studentId.equals(draft.studentId) : draft.studentId != null) return false;
        if (lastEdit != null ? !lastEdit.equals(draft.lastEdit) : draft.lastEdit != null) return false;

        return true;
    }

    /*
    Override hashCode function.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (lastEdit != null ? lastEdit.hashCode() : 0);
        return result;
    }

    // The getter/setter function of variable title.
    @Basic
    @Column(name = "title", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // The getter/setter function of variable attachments.
    @OneToMany(mappedBy = "draft")
    public Collection<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<Attachment> attachments) {
        this.attachments = attachments;
    }

    /*
    Override toString function
    Return the string in the following format.
     */
    @Override
    public String toString() {
        return "{ID: " + id +"; Title: " + title + "; Date: " + lastEdit.toString() +
                "; Student: " + student.toString();
    }

    @Basic
    @Column(name = "amount", nullable = true, precision = 0)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "sum", nullable = true, precision = 0)
    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Basic
    @Column(name = "pay_back_years", nullable = true)
    public Integer getPayBackYears() {
        return payBackYears;
    }

    public void setPayBackYears(Integer payBackYears) {
        this.payBackYears = payBackYears;
    }
}
