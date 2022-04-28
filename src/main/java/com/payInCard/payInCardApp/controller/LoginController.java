package com.payInCard.payInCardApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payInCard.payInCardApp.Security.JwtProvider;
import com.payInCard.payInCardApp.payload.LoginDto;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginDto loginDto){
	try {	
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginDto.getUsername(), 
					loginDto.getPassword()));
		System.out.println("Hello");
		String token = jwtProvider.generateToken(loginDto.getUsername());
		return ResponseEntity.ok(token);
	}
	catch(BadCredentialsException e) {
		return ResponseEntity.status(401).body("Login or password error!");
	}
	}
}
