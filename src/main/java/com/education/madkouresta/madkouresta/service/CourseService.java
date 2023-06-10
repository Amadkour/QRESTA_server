package com.education.madkouresta.madkouresta.service;


import com.education.madkouresta.madkouresta.entity.AppUser;
import com.education.madkouresta.madkouresta.entity.Course;
import com.education.madkouresta.madkouresta.entity.Session;
import com.education.madkouresta.madkouresta.repo.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SessionService sessionService;
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

    public Course addCourse(Course course, String instructorUserName) {
        course.setInstructor(userService.findByUserName(instructorUserName).get());
        return courseRepository.save(course);
    }

    public Course addStudentToCourse(String username, long courseId) throws Exception {
        Course course = courseRepository.getOne(courseId);

        Set<AppUser> students = course.getStudents();
        students.add(userService.findByUserName(username).get());
        course.setStudents(students);
        return courseRepository.save(course);

    }

    public Session addSessionToCourse(Session session, long courseId) throws Exception {
        sessionService.createSession(session);
        session.setCourseId(courseId);
        return sessionService.createSession(session);
    }


    public String getSessionCode(long sessionId) {
        return sessionService.getOne(sessionId).getSessionCode();
    }

    public Set<AppUser> getAllStudents(long courseId) {
        return courseRepository.findById(courseId).get().getStudents();
    }


    public Optional<Course> getCourse(long courseId) {
        return courseRepository.findById(courseId);
    }

    public List<Session> getAllSessions(long courseId) {
        return sessionService.getAllSessionsByCourseId(courseId);
    }

    public Set<Course> getAllStudentCourses(String studentUserName) {
        return courseRepository.findAll().stream().filter(c -> c.getStudents().stream().map(AppUser::getUserName)
                .collect(Collectors.toList()).contains(studentUserName)).collect(Collectors.toSet());
    }

    public List<Map<String, Object>> getStudentAttendance(long course_id, long student_id) {
        List<Session> sessions = getAllSessions(course_id);
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        AppUser user = userService.findById(student_id);
        for (Session session : sessions) {
            Set<AppUser> users = new HashSet<>();
            Map<String, Object> map = session.toMap();
            if (session.getStudents().stream().anyMatch(s -> s.getId() == student_id)) {
                users.add(user);
                map.put("attendance", true);
            } else {
                map.put("attendance", false);
            }
            map.remove("students");
            map.remove("courseId");
            map.remove("createAt");
            maps.add(map);

        }
        return maps;
    }
}
