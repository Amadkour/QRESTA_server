package com.education.madkouresta.madkouresta.service;


import com.education.madkouresta.madkouresta.entity.AppUser;
import com.education.madkouresta.madkouresta.entity.Session;
import com.education.madkouresta.madkouresta.repo.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserService userService;

    public List<Session> getSessions(long courseId) {
        return sessionRepository.findByCourseId(courseId);
    }

    public Session addSslSession(Session session) {
        return sessionRepository.save(session);
    }

    public Session addStudentToSession(String username, long sessionId, String code, String macAddress) throws Exception {
        Session session = sessionRepository.getOne(sessionId);
        AppUser user = userService.findByUserName(username).get();
        if (!Objects.equals(code, session.getSessionCode())) {
            throw new Exception("invalid code");

        } else if (!Objects.equals(macAddress, user.getMacAddress())) {
            throw new Exception("please use your  phone");

        } else {
            Set<AppUser> students = session.getStudents();
            students.add(userService.findByUserName(username).get());
            session.setStudents(students);
            return sessionRepository.save(session);
        }
    }

    public void deleteSession(long sessionId) {
        sessionRepository.deleteById(sessionId);
    }

    public void deleteStudentFromSession(String username, long sessionId) {
        Session session = sessionRepository.getOne(sessionId);
        Set<AppUser> students = session.getStudents();
        students.remove(userService.findByUserName(username).get());
        session.setStudents(students);
        sessionRepository.save(session);
    }

    public void updateSession(long sessionId) {
        Session session = sessionRepository.getOne(sessionId);
        session.setSessionCode(String.valueOf(Math.random()));
        sessionRepository.save(session);
    }

    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    public String getSessionCode(long sessionId) {
        return sessionRepository.getOne(sessionId).getSessionCode();
    }

    public Set<AppUser> getAllStudents(long sessionId) {
        return sessionRepository.getOne(sessionId).getStudents();
    }


    public Session getOne(long sessionId) {
        return sessionRepository.getOne(sessionId);
    }

    public List<Session> getAllSessionsByCourseId(long courseId) {
        return sessionRepository.findAll().stream().filter(s -> s.getCourseId() == courseId).collect(Collectors.toList());
    }

    public Session removeStudentFromSession(String userName, long sessionId) {
        Session session = sessionRepository.getOne(sessionId);
        Set<AppUser> students = session.getStudents();
        students.remove(userService.findByUserName(userName).get());
        session.setStudents(students);
        return sessionRepository.save(session);
    }
    public Set<Session> getAllStudentSessions(String studentUserName) {
       return sessionRepository.findAll().stream().filter(c -> c.getStudents().stream().map(AppUser::getUserName)
                .collect(Collectors.toList()).contains(studentUserName)).collect(Collectors.toSet());
    }

    public Session setLock(boolean lock, long sessionId) {
        Session session = sessionRepository.getOne(sessionId);
        session.setLocked(lock);
        return sessionRepository.save(session);
    }

    public List<Session> findById(long courseId) {
        return sessionRepository.findByCourseId(courseId);
    }
}
