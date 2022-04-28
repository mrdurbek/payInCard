package com.payInCard.payInCardApp.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.payInCard.payInCardApp.Service.MyAuthService;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	MyAuthService myAuthService;
	@Autowired
	JwtProvider jwtProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token  = request.getHeader("Authorization");
		if(token!=null && token.startsWith("Bearer")) {
			token = token.substring(7);
			boolean validateToken = jwtProvider.validateToken(token);
			if(validateToken) {
				String username = jwtProvider.getUsername(token);
				UserDetails userDetails = myAuthService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		
		filterChain.doFilter(request, response);
	}
}
