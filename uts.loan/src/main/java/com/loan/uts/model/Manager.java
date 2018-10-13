package com.loan.uts.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Define Manager Class.
 */
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
    private Administrator adminByAdminId;

    public Manager(){}

    public Manager(String firstname, String lastname, String email, String mobile, String password){
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.deleted = false;
        this.mobile = mobile;
        this.password = password;
    }

    // The getter/setter function of variable Id.
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // The getter/setter function of variable firstname.
    @Basic
    @Column(name = "firstname", nullable = true, length = 10)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    // The getter/setter function of variable lastname.
    @Basic
    @Column(name = "lastname", nullable = true, length = 10)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // The getter/setter function of variable email.
    @Basic
    @Column(name = "email", nullable = true, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // The getter/setter function of variable mobile.
    @Basic
    @Column(name = "mobile", nullable = true, length = 20)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    // The getter/setter function of variable password.
    @Basic
    @Column(name = "password", nullable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // The judge function of deleted or not.
    @Basic
    @Column(name = "deleted", nullable = false)
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /*
    Override equals function
    Judge the equation of two Manager classes through all variables above.
     */
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

    /*
    Override hashCode function.
     */
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

    // The getter/setter function of Collections Applications.
    @OneToMany(mappedBy = "manager")
    public Collection<Application> getApplications() {
        return applications;
    }

    public void setApplications(Collection<Application> applications) {
        this.applications = applications;
    }

    @ManyToOne
    @JoinColumn(name = "admin_id", referencedColumnName = "id", nullable = false)
    public Administrator getAdminByAdminId(){
        return adminByAdminId;
    }

    public void setAdminByAdminId(Administrator adminByAdminId){
        this.adminByAdminId = adminByAdminId;
    }

    /*
    Override toString function
    Return the string in the following format.
     */
    @Override
    public String toString() {
        return "No. " + getId() + " " + getFirstname() + " " + getLastname();
    }
}
