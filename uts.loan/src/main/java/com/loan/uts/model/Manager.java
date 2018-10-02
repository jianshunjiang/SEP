package com.loan.uts.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "manager", schema = "uts_loan")
public class Manager implements Serializable {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String mobile;
    private String password;
    private boolean deleted;
    private Collection<Application> applications;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstname", nullable = true, length = 10)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = true, length = 10)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "mobile", nullable = true, length = 20)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "deleted", nullable = false)
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manager manager = (Manager) o;

        if (id != manager.id) return false;
        if (deleted != manager.deleted) return false;
        if (firstname != null ? !firstname.equals(manager.firstname) : manager.firstname != null) return false;
        if (lastname != null ? !lastname.equals(manager.lastname) : manager.lastname != null) return false;
        if (email != null ? !email.equals(manager.email) : manager.email != null) return false;
        if (mobile != null ? !mobile.equals(manager.mobile) : manager.mobile != null) return false;
        if (password != null ? !password.equals(manager.password) : manager.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (deleted ? 1 : 0);
        return result;
    }

    @OneToMany(mappedBy = "manager")
    public Collection<Application> getApplications() {
        return applications;
    }

    public void setApplications(Collection<Application> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "No. " + getId() + " " + getFirstname() + " " + getLastname();
    }
}
