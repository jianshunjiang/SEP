package com.loan.uts.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

/**
 * Define Student Class.
 */
@Entity
@Table(name = "student", schema = "uts_loan", catalog = "")
public class Student implements Serializable {
    private Integer id;
    private String password;
    private String bankaccount;
    private String email;
    private String phone;
    private String faculty;
    private String firstname;
    private String lastname;
    private String course;
    private Date dob;
    private String gender;
    private String nationality;
    private String startYear;
    private Set<Application> applications;
    private Draft draft;

    // The getter/setter function of variable draft.
    @OneToOne
    @JoinColumn(name = "draft_id", referencedColumnName = "id")
    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }

    // The getter/setter function of variable id.
    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    // The getter/setter function of variable bankaccount.
    @Basic
    @Column(name = "bankaccount", nullable = true, length = 100)
    public String getBankaccount() {
        return bankaccount;
    }

    public void setBankaccount(String bankaccount) {
        this.bankaccount = bankaccount;
    }

    // The getter/setter function of variable email.
    @Basic
    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // The getter/setter function of variable phone.
    @Basic
    @Column(name = "phone", nullable = true, length = 50)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // The getter/setter function of variable faculty.
    @Basic
    @Column(name = "faculty", nullable = true, length = 4)
    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
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

    // The getter/setter function of variable course.
    @Basic
    @Column(name = "course", nullable = true, length = 6)
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // The getter/setter function of variable dob.
    @Basic
    @Column(name = "dob", nullable = true)
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    // The getter/setter function of variable gender.
    @Basic
    @Column(name = "gender", nullable = true, length = 6)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // The getter/setter function of variable nationality.
    @Basic
    @Column(name = "nationality", nullable = true, length = 20)
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    // The getter/setter function of variable startyear.
    @Basic
    @Column(name = "start_year", nullable = true, length = 4)
    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    /*
    Override equals function
    Judge the equation of two Student classes through all variables above.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != null ? !id.equals(student.id) : student.id != null) return false;
        if (password != null ? !password.equals(student.password) : student.password != null) return false;
        if (bankaccount != null ? !bankaccount.equals(student.bankaccount) : student.bankaccount != null) return false;
        if (email != null ? !email.equals(student.email) : student.email != null) return false;
        if (phone != null ? !phone.equals(student.phone) : student.phone != null) return false;
        if (faculty != null ? !faculty.equals(student.faculty) : student.faculty != null) return false;
        if (firstname != null ? !firstname.equals(student.firstname) : student.firstname != null) return false;
        if (lastname != null ? !lastname.equals(student.lastname) : student.lastname != null) return false;
        if (course != null ? !course.equals(student.course) : student.course != null) return false;
        if (dob != null ? !dob.equals(student.dob) : student.dob != null) return false;
        if (gender != null ? !gender.equals(student.gender) : student.gender != null) return false;
        if (nationality != null ? !nationality.equals(student.nationality) : student.nationality != null) return false;
        if (startYear != null ? !startYear.equals(student.startYear) : student.startYear != null) return false;

        return true;
    }

    /*
    Override hashCode function.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (bankaccount != null ? bankaccount.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (faculty != null ? faculty.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (dob != null ? dob.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (startYear != null ? startYear.hashCode() : 0);
        return result;
    }

    // The getter/setter function of Set of applications.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    public Set<Application> getApplications() {
        return applications;
    }

    public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

    /*
    Override toString function
    Return the string in the following format.
     */
    @Override
    public String toString() {
        return "ID: " + id +"; Name: " + firstname + " " + lastname;
    }
}
