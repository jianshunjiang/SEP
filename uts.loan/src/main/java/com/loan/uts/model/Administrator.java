package com.loan.uts.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Define Administrator class.
 */
@Entity
@Table(name = "admin", schema = "uts_loan", catalog = "")

public class Administrator implements Serializable {
    private Integer id;
    private String username;
    private String password;

    /**
     * the constructor of Administrator class.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
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

    // The getter/setter function of variable username.
    @Basic
    @Column(name = "username", nullable = false, length = 20)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // The getter/setter function of variable password.
    @Basic
    @Column(name = "password", nullable = false, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
    Override equals function
    Judge the equation of two Administrator classes through ID, username and password.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Administrator that = (Administrator) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    /*
    Override hashCode function
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
