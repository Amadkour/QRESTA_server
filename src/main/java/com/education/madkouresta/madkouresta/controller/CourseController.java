package com.education.madkouresta.madkouresta.controller;

import com.education.madkouresta.madkouresta.entity.AppUser;
import com.education.madkouresta.madkouresta.entity.Course;
import com.education.madkouresta.madkouresta.entity.Session;
import com.education.madkouresta.madkouresta.service.CourseService;
import com.education.madkouresta.madkouresta.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping(value = "/api/v1/course")
public class CourseController {
    Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;
    @Autowired
    private SessionService sessionService;

    @GetMapping(value = {"", "/students"})
    public Set<AppUser> getAllStudents(@RequestHeader long course_id) throws Exception {
        return courseService.getAllStudents(course_id);
    }

    @PostMapping(value = "/add_student")
    public Course addStudentToCourse(@RequestHeader String userName, @RequestHeader long courseId) throws Exception {
        return courseService.addStudentToCourse(userName, courseId);

    }

    @PostMapping(value = "/new_session")
    public Course generateSessionOnCourse(@RequestHeader long courseId) throws Exception {
        Session s = new Session();
        s.setSessionCode(String.valueOf(Math.random()));
        System.out.println(s.getSessionCode());
        return courseService.addSessionToCourse(sessionService.createSession(s), courseId);
    }

    @PostMapping(value = "/add_course")
    public Course addCourse(@ModelAttribute Course course) throws Exception {
        return courseService.addCourse(course);


    }
}
