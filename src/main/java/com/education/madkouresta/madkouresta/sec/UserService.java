package com.education.madkouresta.madkouresta.sec;

import com.education.madkouresta.madkouresta.entity.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

import java.util.*;
@Configuration
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
    @Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<AppUser> user = userRepository.findByUserName(userName);

		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

		return user.map(MyUserDetails::new).get();
	}

	public void addUser(AppUser user) {
		user.setPassword(getPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
	}

	public List<AppUser> getAllUsers() {

		return userRepository.findAll();
	}

	public Optional<AppUser> findByUserName(String userName) {

		return userRepository.findByUserName(userName);
	}

}
