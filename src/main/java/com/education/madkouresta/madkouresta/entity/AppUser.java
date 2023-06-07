package com.education.madkouresta.madkouresta.entity;

import com.education.madkouresta.madkouresta.entity.*;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.lang.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "SEC_USERS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id ;
	@NonNull
	private String userName ;
	@JsonIgnore
	private String password ;
	private boolean active;
    private String roles;       // "ADMIN_ROLE" , "USER_ROLE"
    @NonNull
    private String phone;
    @NonNull
    private String mail;
    @NonNull
    private String macAddress;
    private boolean isStudent;
    private boolean isMail;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @ManyToMany
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    Set<Course> studentCourses;

    @ManyToMany
    @JoinTable(
            name = "student_sessions",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    Set<Session> sessions;

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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
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

    public boolean isMail() {
        return isMail;
    }

    public void setMail(boolean mail) {
        isMail = mail;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<Course> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<Course> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }
}
