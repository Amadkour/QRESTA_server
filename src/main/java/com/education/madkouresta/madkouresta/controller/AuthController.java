package com.education.madkouresta.madkouresta.controller;

import com.education.madkouresta.madkouresta.entity.*;
import com.education.madkouresta.madkouresta.sec.JWTResponse;
import com.education.madkouresta.madkouresta.sec.TokenUtiles;
import com.education.madkouresta.madkouresta.service.UserService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(value = "/api/v1/auth")
//@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
	Logger log = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	private TokenUtiles tokenUtils;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = { "", "/login" })
		public JWTResponse singIn(@ModelAttribute AppUser appUser) throws Exception {
		Authentication authentication = null;
		String username = appUser.getUserName();

		try {
			log.info("authentication >> ");
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, appUser.getPassword() ));

		} catch (DisabledException dis) {
			return new JWTResponse(null, null, null, "USER_DISABLED");
		} catch (BadCredentialsException e) {
			return new JWTResponse(e.toString(), null, null, "INVALID_CREDENTIALS");
			}catch (Exception e) {
			return new JWTResponse(null, null, null, e.getMessage());
		}

		log.info("authentication >> " + authentication.isAuthenticated());
		if (authentication.isAuthenticated()) {
			log.info("authentication >> " + authentication.isAuthenticated());
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails userDetails = userService.loadUserByUsername(username);
			Optional<AppUser> user = userService.findByUserName(username);

			String token = tokenUtils.generateToken(userDetails);

			return new JWTResponse(token, appUser.getMail() , user.get().getRole(), "SUCCESS");
		}

	return new JWTResponse(null, null, null, "INVALID");
	}
    @PostMapping(value = "/register" )
    public AppUser singUp(@ModelAttribute AppUser appUser) throws Exception {
//        AppUser user=appUser;
//        user.setActive(true);
//        user.setUserName(userName);
//        user.setPassword(password);
//        user.setMacAddress("qqqwweerrr");
//        user.setRoles("USER_ROLE");
      return  userService.addUser(appUser);
    }

	@GetMapping("/users")
	public List<AppUser> getAllUsers() {
		return userService.getAllUsers();
	}

}
