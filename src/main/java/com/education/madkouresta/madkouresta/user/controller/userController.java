package com.education.madkouresta.madkouresta.user.controller;

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
@RequestMapping("/api/user/")

public class userController {
    @Autowired
    private userService userService;
    @Autowired
    private CourseService courseService;

    @GetMapping("book/{userId}/{courseId}")
    User book(@PathVariable Integer userId, @PathVariable Integer courseId) {
        final User user = userService.getUserById(userId);
        final Course course = courseService.getCourse(courseId);
        // add student to course
        final List<Course> courses = user.getCourses();
        courses.add(0, course);
        user.setCourses(courses);
        return userService.updateUser(user);
    }

    @PostMapping("/create")
    User saveUser(@RequestBody User user) {
        return userService.savUser(user);
    }
}
