package com.loan.uts.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Define Attachment class.
 */
@Entity
@Table(name = "attachment", schema = "uts_loan", catalog = "")
public class Attachment implements Serializable {
    private Integer id;
    private Date uploadDate;
    private String path;
    private Draft draft;
    private Application application;

    /**
     * The constructor of Attachment.
     */
    public Attachment(){}

    /**
     * The constructor of Attachment.
     * @param path
     * @param application
     * @param uploadDate
     */
    public Attachment(String path, Application application, Date uploadDate){
        this.path = path;
        this.application = application;
        this.uploadDate = uploadDate;
    }
    // The getter/setter function of variable ID.
    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // The getter/setter function of variable upLoadDate.
    @Basic
    @Column(name = "upload_date", nullable = true)
    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    // The getter/setter function of variable path.
    @Basic
    @Column(name = "path", nullable = true, length = 200)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /*
    Override equals function
    Judge the equation of two Attachment classes through the variables above.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attachment that = (Attachment) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (uploadDate != null ? !uploadDate.equals(that.uploadDate) : that.uploadDate != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;

        return true;
    }

    /*
    Override hashCode function.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (uploadDate != null ? uploadDate.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }

    // The getter/setter function of variable draft.
    @ManyToOne
    @JoinColumn(name = "draft_id", referencedColumnName = "id")
    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }

    // The getter/setter function of variable application.
    @ManyToOne
    @JoinColumn(name = "application_id", referencedColumnName = "id")
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
