package com.education.madkouresta.madkouresta.controller;

import com.education.madkouresta.madkouresta.entity.AppUser;
import com.education.madkouresta.madkouresta.entity.Course;
import com.education.madkouresta.madkouresta.entity.Session;
import com.education.madkouresta.madkouresta.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping(value = "/api/v1/session")
public class SessionController {
    Logger log = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionService sessionService;


    @GetMapping(value = {"", "/students"})
    public Set<AppUser> getAllStudents(@RequestHeader long session_id) throws Exception {
        return sessionService.getAllStudents(session_id);
    }

    @GetMapping(value = "/attendance")
    public Session addStudentToSession(@RequestHeader String user_name, @RequestHeader long session_id, @RequestHeader String code,@RequestHeader String mac_address) throws Exception {
        return sessionService.addStudentToSession(user_name, session_id, code, mac_address);

    }
    @GetMapping(value = "/remove_student")
    public Session removeStudentFromSession(@RequestHeader String user_name, @RequestHeader long session_id) throws Exception {
        return sessionService.removeStudentFromSession(user_name, session_id);

    }
    @PostMapping(value = "/add_session")
    public Session addSession(@ModelAttribute Session session) throws Exception {
        return sessionService.createSession(session);

    }
    @PostMapping(value = "/toggle_session")
    public Session toggelSession(@RequestHeader boolean lock, @RequestHeader long sessionId) throws Exception {
        return sessionService.setLock(lock,sessionId);

    }
    @GetMapping(value =  "/student_sessions")
    public Set<Session> getAllStudentSessions(@RequestHeader String student_user_name) throws Exception {

        return sessionService.getAllStudentSessions(student_user_name);
    }

}
