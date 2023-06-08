package com.education.madkouresta.madkouresta.entity;

import com.education.madkouresta.madkouresta.entity.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.lang.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "SEC_USER")
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    @Column(unique = true)
    private String userName;
    @JsonIgnore
    private String password;
    private boolean active;
    private String role;       // "ADMIN_ROLE" , "USER_ROLE"
    @NonNull
    private String phone;
    @NonNull
    private String mail;
    @NonNull
    private String macAddress;
    @NonNull
    private boolean isStudent;
    @NonNull
    private boolean isMale;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @ManyToMany
    private Set<Course> courses = new HashSet<>();
    @ManyToMany
    private Set<Session> sessions = new HashSet<>();


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @NonNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NonNull String phone) {
        this.phone = phone;
    }

    @NonNull
    public String getMail() {
        return mail;
    }

    public void setMail(@NonNull String mail) {
        this.mail = mail;
    }

    @NonNull
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(@NonNull String macAddress) {
        this.macAddress = macAddress;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean mail) {
        isMale = mail;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<Course> getStudentCourses() {
        return courses;
    }

    public void setStudentCourses(Set<Course> studentCourses) {
        this.courses = studentCourses;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }
}
