package com.education.madkouresta.madkouresta.entity;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "COURSE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
    @NonNull
    private String courseName ;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate ;
    @ManyToMany(mappedBy = "course_students")
    Set<AppUser> students;

    @OneToMany(mappedBy = "session")
    Set<Session> sessions;
}
