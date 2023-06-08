package com.education.madkouresta.madkouresta.sec;

import com.education.madkouresta.madkouresta.service.UserService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.*;
import org.springframework.web.filter.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class AuthFilter  extends OncePerRequestFilter{

	private String TOKEN_HEADER = "Authorization" ;
	@Autowired
     private UserService userService ;
	@Autowired
	private TokenUtiles tokenUtil ;
	
	
	@Override
	 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(TOKEN_HEADER);
        final SecurityContext securityContext = SecurityContextHolder.getContext();

        if (header != null && securityContext.getAuthentication() == null) {
            String token = header.substring("Bearer ".length());
            String username = tokenUtil.getUserNameFromToken(token);
            if(username != null) {
                UserDetails userDetails = userService.loadUserByUsername(username);
                if (tokenUtil.isTokenValid(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }


        filterChain.doFilter(request, response);
    }
}
