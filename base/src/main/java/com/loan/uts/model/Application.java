package com.loan.uts.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "application", schema = "uts_loan")
public class Application {

    public static final String SUBMITTED = "Submitted";
    public static final String ACCEPTED = "Accepted";
    public static final String REFUSED = "Refused";
    public static final String PROCESSING = "Processing";
    private Integer id;
    private String status;
    private Date submitDate;
    private Date resultDate;
    private String content;
    private Student student;
    private Manager manager;
    private String title;
    private String comment;
    private Collection<Attachment> attachmentsById;

    public Application(){}

    public Application(String title, String content, Date submitDate, String status, Student student){
        this.title = title;
        this.content = content;
        this.submitDate = submitDate;
        this.status = status;
        this.student = student;
    }

    /**
     *
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "submit_date", nullable = true)
    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    @Basic
    @Column(name = "result_date", nullable = true)
    public Date getResultDate() {
        return resultDate;
    }

    public void setResultDate(Date resultDate) {
        this.resultDate = resultDate;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 5000)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Application that = (Application) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (submitDate != null ? !submitDate.equals(that.submitDate) : that.submitDate != null) return false;
        if (resultDate != null ? !resultDate.equals(that.resultDate) : that.resultDate != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (submitDate != null ? submitDate.hashCode() : 0);
        result = 31 * result + (resultDate != null ? resultDate.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 50)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String resultDateString(){
        return resultDate.toString().split(" ")[0];
    }

    public String submitDateString(){
        return submitDate.toString().split(" ")[0];
    }

    @Basic
    @Column(name = "comment", nullable = true, length = 1000)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @OneToMany(mappedBy = "application")
    public Collection<Attachment> getAttachmentsById() {
        return attachmentsById;
    }

    public void setAttachmentsById(Collection<Attachment> attachmentsById) {
        this.attachmentsById = attachmentsById;
    }


}
