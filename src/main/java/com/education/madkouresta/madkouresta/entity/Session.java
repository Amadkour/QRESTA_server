package com.education.madkouresta.madkouresta.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "COURSE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String sessionCode;

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Date createAt;
    @ManyToMany( cascade = CascadeType.ALL)
    private Set<AppUser> students;

    @ManyToOne
    private Course course;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSessionCode() {
        return sessionCode;
    }

    public void setSessionCode(String sessionCode) {
        this.sessionCode = sessionCode;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Set<AppUser> getStudents() {
        return students;
    }

    public void setStudents(Set<AppUser> students) {
        this.students = students;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
