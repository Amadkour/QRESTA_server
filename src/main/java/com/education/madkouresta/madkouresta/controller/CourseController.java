package com.education.madkouresta.madkouresta.controller;

import com.education.madkouresta.madkouresta.entity.AppUser;
import com.education.madkouresta.madkouresta.entity.Course;
import com.education.madkouresta.madkouresta.entity.Session;
import com.education.madkouresta.madkouresta.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping(value = "/api/v1/course")
public class CourseController {
    Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @GetMapping(value = {"", "/students"})
    public Set<AppUser> getAllStudents(@RequestHeader long course_id) throws Exception {

        return courseService.getAllStudents(course_id);
    }
    @GetMapping(value =  "/student_courses")
    public Set<Course> getAllStudentCourse(@RequestHeader String student_user_name) throws Exception {

        return courseService.getAllStudentCourses(student_user_name);
    }


    @PostMapping(value = "/add_student")
    public Course addStudentToCourse(@RequestHeader String userName, @RequestHeader long courseId) throws Exception {
        return courseService.addStudentToCourse(userName, courseId);

    }

    @PostMapping(value = "/new_session")
    public Session generateSessionOnCourse(@RequestHeader long courseId) throws Exception {
        Session s = new Session();
        s.setSessionCode(String.valueOf(Math.random()));
        return courseService.addSessionToCourse(s, courseId);
    }

    @PostMapping(value = "/add_course")
    public Course addCourse(@ModelAttribute Course course,@RequestHeader String instructorUserName) throws Exception {
        return courseService.addCourse(course,instructorUserName);


    }

    @GetMapping(value = "/get_code")
    public String getSessionCode(@RequestHeader long session_id) throws Exception {
        return courseService.getSessionCode(session_id);
    }

    @GetMapping(value = "/details")
    public Optional<Course> getCourse(@RequestHeader long course_id) throws Exception {
        return courseService.getCourse(course_id);
    }

    @GetMapping(value = "/sessions")
    public List<Session> getSession(@RequestHeader long course_id) throws Exception {
        return courseService.getAllSessions(course_id);
    }
    @GetMapping(value = "/student_attendance")
    public List<Map<String, Object>> getStatistics(@RequestHeader long course_id, @RequestHeader long student_id) throws Exception {
        return courseService.getStudentAttendance(course_id,student_id);
    }
}
