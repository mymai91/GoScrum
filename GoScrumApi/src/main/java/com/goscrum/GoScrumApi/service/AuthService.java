package com.GoScrum.GoScrumApi.service;

import com.GoScrum.GoScrumApi.payload.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
}
