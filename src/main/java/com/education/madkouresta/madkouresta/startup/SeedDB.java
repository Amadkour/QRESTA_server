package com.education.madkouresta.madkouresta.startup;

import com.education.madkouresta.madkouresta.entity.AppUser;
import com.education.madkouresta.madkouresta.entity.Course;
import com.education.madkouresta.madkouresta.entity.Session;
import com.education.madkouresta.madkouresta.service.CourseService;
import com.education.madkouresta.madkouresta.service.SessionService;
import com.education.madkouresta.madkouresta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SeedDB implements CommandLineRunner {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;
    @Autowired
    SessionService sessionService;

    @Override
    public void run(String... args) throws Exception {
        ///instructor
        AppUser user = new AppUser();
        user.setUserName("Madkour");
        user.setPassword("admin");
        user.setMail("anpch@example.com");
        user.setMacAddress("22222222");
        user.setRole("USER_ROLE");
        user.setStudent(false);
        user.setMale(true);
        user.setPhone("01090962505");
        user.setActive(true);
        user = userService.addUser(user);
        ///student1
        AppUser std1 = new AppUser();
        std1.setUserName("Madkour1");
        std1.setPassword("admin");
        std1.setMail("anpch@example.com");
        std1.setMacAddress("22222222");
        std1.setRole("USER_ROLE");
        std1.setStudent(true);
        std1.setMale(true);
        std1.setPhone("01090962505");
        std1.setActive(true);
        std1 = userService.addUser(std1);
        ///student1
        AppUser std0 = new AppUser();
        std0.setUserName("Madkour0");
        std0.setPassword("admin");
        std0.setMail("anpch@example.com");
        std0.setMacAddress("22222222");
        std0.setRole("USER_ROLE");
        std0.setStudent(true);
        std0.setMale(true);
        std0.setPhone("01090962505");
        std0.setActive(true);
        std0 = userService.addUser(std1);
        ///student2
        AppUser std2 = new AppUser();
        std2.setUserName("Madkour2");
        std2.setPassword("admin");
        std2.setMail("anpch@example.com");
        std2.setMacAddress("22222222");
        std2.setRole("USER_ROLE");
        std2.setStudent(true);
        std2.setMale(true);
        std2.setPhone("01090962505");
        std2.setActive(true);
        std2 = userService.addUser(std1);
        ///course
        Course c = new Course();
        Set<AppUser> students = new HashSet<AppUser>();
        students.add(std1);
        students.add(std2);
        c.setStudents(students);
        courseService.addCourse(c, "Madkour");
        ///session
        Session session=new Session();
        sessionService.createSession(session);
        sessionService.addStudentToSession("madkour1",session.getId(),session.getSessionCode(),std1.getMacAddress());

    }
}
