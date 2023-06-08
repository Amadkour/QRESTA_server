package com.education.madkouresta.madkouresta.repo;

import com.education.madkouresta.madkouresta.entity.AppUser;
import com.education.madkouresta.madkouresta.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long > {


	List<Session> findByCourseId(long courseId);
}
