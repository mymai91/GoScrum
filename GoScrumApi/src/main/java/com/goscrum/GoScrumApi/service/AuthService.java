package com.GoScrum.GoScrumApi.service;

import com.GoScrum.GoScrumApi.payload.LoginDto;
import com.GoScrum.GoScrumApi.payload.RegisterDto;

public interface AuthService {
	String register(RegisterDto registerDto);

	String login(LoginDto loginDto);
}
