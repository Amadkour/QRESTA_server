package com.education.madkouresta.madkouresta.sec;

import com.education.madkouresta.madkouresta.entity.*;
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
		public JWTResponse singIn(@RequestParam String userName , @RequestParam String password) throws Exception {
		log.info("authentication >> ");
		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userName, password ));

		} catch (DisabledException dis) {
			return new JWTResponse(null, null, null, "USER_DISABLED");
		} catch (BadCredentialsException e) {
			return new JWTResponse(e.toString(), null, null, "INVALID_CREDENTIALS");
			}

		log.info("authentication >> " + authentication.isAuthenticated());
		if (authentication.isAuthenticated()) {
			log.info("authentication >> " + authentication.isAuthenticated());
			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails userDetails = userService.loadUserByUsername(userName);
			Optional<AppUser> user = userService.findByUserName(userName);

			String token = tokenUtils.generateToken(userDetails);

			return new JWTResponse(token, userName , user.get().getRoles(), "SUCCESS");
		}

	return new JWTResponse(null, null, null, "INVALID");
	}
    @PostMapping(value = "/registration" )
    public void singUp(@RequestParam String userName , @RequestParam String password) throws Exception {
        AppUser user=new AppUser();
        user.setActive(true);
        user.setUserName(userName);
        user.setPassword(password);
        user.setMacAddress("qqqwweerrr");
        user.setRoles("USER_ROLE");
        userService.addUser(user);
    }

	@GetMapping("/users")
	public List<AppUser> getAllUsers() {
		return userService.getAllUsers();
	}

}
