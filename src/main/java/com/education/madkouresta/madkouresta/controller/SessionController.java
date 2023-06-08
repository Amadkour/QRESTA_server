package com.education.madkouresta.madkouresta.controller;

import com.education.madkouresta.madkouresta.entity.AppUser;
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

    @PostMapping(value = "/attendance")
    public Session addStudentToSession(@ModelAttribute String userName, @ModelAttribute long sessionId, @ModelAttribute String code) throws Exception {
        return sessionService.addStudentToSession(userName, sessionId, code);

    }
    @PostMapping(value = "/add_session")
    public Session addSession(@ModelAttribute Session session) throws Exception {
        return sessionService.createSession(session);

    }
}
