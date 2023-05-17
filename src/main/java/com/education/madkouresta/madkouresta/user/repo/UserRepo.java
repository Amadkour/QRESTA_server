package com.education.madkouresta.madkouresta.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.education.madkouresta.madkouresta.user.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
