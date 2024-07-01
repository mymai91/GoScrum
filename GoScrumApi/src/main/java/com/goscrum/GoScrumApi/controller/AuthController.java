package com.GoScrum.GoScrumApi.controller;

import com.GoScrum.GoScrumApi.payload.JWTAuthResponse;
import com.GoScrum.GoScrumApi.payload.LoginDto;
import com.GoScrum.GoScrumApi.payload.RegisterDto;
import com.GoScrum.GoScrumApi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
		String response = authService.register(registerDto);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto) {
		String token = authService.login(loginDto);

		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setToken(token);

		return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
	}
}
