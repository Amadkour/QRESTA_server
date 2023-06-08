package com.education.madkouresta.madkouresta.repo;

import com.education.madkouresta.madkouresta.entity.Course;
import com.education.madkouresta.madkouresta.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long > {

}
