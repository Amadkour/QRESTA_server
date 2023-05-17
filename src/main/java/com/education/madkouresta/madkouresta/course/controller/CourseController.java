package com.education.madkouresta.madkouresta.course.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.madkouresta.madkouresta.course.model.Course;
import com.education.madkouresta.madkouresta.course.service.CourseService;
import com.education.madkouresta.madkouresta.user.model.User;
import com.education.madkouresta.madkouresta.user.service.userService;

@RestController
@RequestMapping("/api/course/")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private userService userService;

    @PostMapping("create")
    Course createCourse(@RequestBody Course course) {
        return courseService.savCourse(course);
    }

    @GetMapping("all")
    List<Course> getAllCourse() {
        return courseService.getAllCourses();
    }

    @GetMapping("book/{userId}/{courseId}")
    Course savr(@PathVariable Integer userId, @PathVariable Integer courseId) {
        User use = userService.getUserById(userId);
        Course course = courseService.getCourse(courseId);
        // add student to course
        List<User> students = course.getStudents();
        students.add(0, use);
        course.setStudents(students);
        return courseService.updatCourse(course);
    }
}
