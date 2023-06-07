package com.education.madkouresta.madkouresta.sec;


import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.http.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final String [] PUBLIC_ENDPOINTS = {
			
			"/api/v1/auth/**", "/v2/api-docs", 
			"/configuration/ui", "/swagger-resources", 
			"/configuration/security", "/swagger-ui.html",
			"/webjars/**","/swagger-resources/configuration/ui"
	};
	
	@Autowired
    UserDetailsService userDetailsService;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
			}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.authorizeRequests()
		.antMatchers(PUBLIC_ENDPOINTS).permitAll()
		.anyRequest().authenticated()
		.and()
	.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Bean
	public AuthFilter authFilter () {
		
		return new AuthFilter() ;
	}

}
