package com.education.madkouresta.madkouresta.service;


import com.education.madkouresta.madkouresta.entity.AppUser;
import com.education.madkouresta.madkouresta.entity.Session;
import com.education.madkouresta.madkouresta.repo.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Configuration
@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserService userService;

    public List<Session> getSessions( long courseId) {
        return sessionRepository.findByCourseId(courseId);
    }

    public Session addSslSession(Session session) {
        return sessionRepository.save(session);
    }

    public Session addStudentToSession(String username, long sessionId,String code) throws Exception {
        Session session = sessionRepository.getOne(sessionId);
        if(Objects.equals(code, session.getSessionCode())) {
            Set<AppUser> students = session.getStudents();
            students.add(userService.findByUserName(username).get());
            session.setStudents(students);
            return sessionRepository.save(session);
        }else {
            throw new Exception("invalid code");
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

    public Session createSession(Session session){
        return sessionRepository.save(session);
    }
    public String getSessionCode(long sessionId) {
        return sessionRepository.getOne(sessionId).getSessionCode();
    }
    public Set<AppUser> getAllStudents(long sessionId) {
        return sessionRepository.getOne(sessionId).getStudents();
    }


}
