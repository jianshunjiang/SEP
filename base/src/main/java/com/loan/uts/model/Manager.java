package com.loan.uts.model;

import javax.persistence.*;

@Entity
@Table(name = "manager", schema = "uts_loan", catalog = "")
public class Manager {
    private Integer id;
    private String fistname;
    private String lastname;
    private String email;
    private String mobile;
    private String password;
    private byte delete;
    private Integer adminId;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "firstname", nullable = true, length = 10)
    public String getFistname() {
        return fistname;
    }

    public void setFistname(String fistname) {
        this.fistname = fistname;
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
    @Column(name = "delete", nullable = false)
    public byte getDelete() {
        return delete;
    }

    public void setDelete(byte delete) {
        this.delete = delete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manager manager = (Manager) o;

        if (delete != manager.delete) return false;
        if (id != null ? !id.equals(manager.id) : manager.id != null) return false;
        if (fistname != null ? !fistname.equals(manager.fistname) : manager.fistname != null) return false;
        if (lastname != null ? !lastname.equals(manager.lastname) : manager.lastname != null) return false;
        if (email != null ? !email.equals(manager.email) : manager.email != null) return false;
        if (mobile != null ? !mobile.equals(manager.mobile) : manager.mobile != null) return false;
        if (password != null ? !password.equals(manager.password) : manager.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fistname != null ? fistname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (int) delete;
        return result;
    }

    @Basic
    @Column(name = "admin_id", nullable = false)
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}
