package com.GoScrum.GoScrumApi.service;

import com.GoScrum.GoScrumApi.entity.User;
import com.GoScrum.GoScrumApi.payload.LoginDto;
import com.GoScrum.GoScrumApi.payload.RegisterDto;
import org.springframework.security.core.userdetails.UserDetails;


public interface AuthService {
	String register(RegisterDto registerDto);

	String login(LoginDto loginDto);

	User getCurrentUser();
}
