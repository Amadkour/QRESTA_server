package com.education.madkouresta.madkouresta.entity;
import lombok.*;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "COURSE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt ;
    @ManyToMany(mappedBy = "session_students")
    Set<AppUser> students;
}
