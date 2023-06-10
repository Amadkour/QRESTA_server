package com.education.madkouresta.madkouresta.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
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
    @CreationTimestamp
    private Date startDate;
    @ManyToOne
    private AppUser instructor;
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<AppUser> students = new HashSet<>();

    public AppUser getInstructor() {
        return instructor;
    }

    public void setInstructor(AppUser instructor) {
        this.instructor = instructor;
    }

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

    public Set<AppUser> getStudents() {
        return students;
    }

    public void setStudents(Set<AppUser> students) {
        this.students = students;
    }
}
