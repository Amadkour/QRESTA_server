package com.education.madkouresta.madkouresta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;


@Entity
@Table(name = "SESSION")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
   @JsonIgnore
    private String sessionCode;
    @Column(columnDefinition = "boolean default false")
    private Boolean locked;

    @CreationTimestamp
    private Date createAt;
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<AppUser> students;
    @NotNull
    private long courseId;

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

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

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }


    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("sessionCode", sessionCode);
        map.put("locked", locked);
        map.put("createAt", createAt);
        map.put("students", students);
        map.put("courseId", courseId);
        return map;
    }
}
