package com.education.madkouresta.madkouresta.repo;

import com.education.madkouresta.madkouresta.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<AppUser,Long > {
	
	Optional<AppUser> findByUserName (String userName);

}
