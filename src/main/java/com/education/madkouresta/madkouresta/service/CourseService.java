package com.education.madkouresta.madkouresta.service;


import com.education.madkouresta.madkouresta.entity.AppUser;
import com.education.madkouresta.madkouresta.entity.Course;
import com.education.madkouresta.madkouresta.entity.Session;
import com.education.madkouresta.madkouresta.repo.CourseRepository;
import com.education.madkouresta.madkouresta.repo.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Configuration
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserService userService;

    public List<Course> getCoursesByUserId(long userId) {
        List<Course> courses = courseRepository.findAll();
        for (Course course : courses) {
            if (!course.getStudents().stream().allMatch(user -> user.getId() == userId)) {
                courses.add(course);
            }
        }
        return courses;
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course addStudentToCourse(String username, long courseId) throws Exception {
        Course course = courseRepository.getOne(courseId);

        Set<AppUser> students = course.getStudents();
        students.add(userService.findByUserName(username).get());
        course.setStudents(students);
        return courseRepository.save(course);

    }
    public Course addSessionToCourse(Session session, long courseId) throws Exception {
        Course course = courseRepository.getOne(courseId);
        System.out.println(course.getCourseName());
        Set<Session> sessions = course.getSessions();
        sessions.add(session);
        course.setSessions(sessions);
        return courseRepository.save(course);

    }


    public String getSessionCode(long sessionId) {
        return sessionRepository.getOne(sessionId).getSessionCode();
    }

    public Set<AppUser> getAllStudents(long courseId) {
        return courseRepository.getOne(courseId).getStudents();
    }


}
