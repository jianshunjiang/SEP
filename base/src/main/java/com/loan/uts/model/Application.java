package com.loan.uts.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "application", schema = "uts_loan", catalog = "")
public class Application {
    private Integer id;
    private String status;
    private Date submitDate;
    private Date resultDate;
    private String content;

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     */
    @Id
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
}
