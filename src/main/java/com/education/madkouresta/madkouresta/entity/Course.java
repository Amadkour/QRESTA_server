package com.education.madkouresta.madkouresta.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "COURSE")
@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private String courseName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_sessions",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "session_id")
    )
    private Set<Session> sessions = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_students",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "app_user_id")
    )
    private Set<AppUser> students = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(@NonNull String courseName) {
        this.courseName = courseName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public Set<AppUser> getStudents() {
        return students;
    }

    public void setStudents(Set<AppUser> students) {
        this.students = students;
    }
}
