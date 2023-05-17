package com.education.madkouresta.madkouresta.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.madkouresta.madkouresta.course.model.Course;
import com.education.madkouresta.madkouresta.course.repo.CourseRepo;

@Service
public class CourseService {
    @Autowired
    private CourseRepo repo;

    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    public Course updatCourse(Course course) {
        return repo.save(course);
    }

    public Course getCourse(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Course savCourse(Course course) {
        return repo.save(course);
    }

    public boolean deletUser(Course course) {
        try {
            repo.delete(course);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Course updateUser(Course course) {
        return repo.save(course);
    }

}
