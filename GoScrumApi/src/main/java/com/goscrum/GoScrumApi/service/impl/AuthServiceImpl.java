package com.GoScrum.GoScrumApi.service.impl;

import com.GoScrum.GoScrumApi.entity.Role;
import com.GoScrum.GoScrumApi.entity.User;
import com.GoScrum.GoScrumApi.payload.LoginDto;
import com.GoScrum.GoScrumApi.payload.RegisterDto;
import com.GoScrum.GoScrumApi.repository.RoleRepository;
import com.GoScrum.GoScrumApi.repository.UserRepository;
import com.GoScrum.GoScrumApi.security.JwtTokenProvider;
import com.GoScrum.GoScrumApi.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	private RoleRepository roleRepository;

	private PasswordEncoder passwordEncoder;

	private AuthenticationManager authenticateManager;

	private JwtTokenProvider jwtTokenProvider;


	public AuthServiceImpl(UserRepository userRepository,
	                       RoleRepository roleRepository,
	                       PasswordEncoder passwordEncoder,
	                       AuthenticationManager authenticateManager,
	                       JwtTokenProvider jwtTokenProvider) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticateManager = authenticateManager;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String register(RegisterDto registerDto) {
		if (userRepository.existsByUsername(registerDto.getUsername())) {
			// TODO: modify another exception
			throw new RuntimeException("Username is already taken!");
		}

		if (userRepository.existsByEmail(registerDto.getEmail())) {
			throw new RuntimeException("Email is already taken!");
		}

		User user = new User();
		user.setName(registerDto.getName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setPassword(registerDto.getPassword());
		user.setDevice_id(registerDto.getDeviceId());
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role userRole = findUserRole();
		roles.add(userRole);
		user.setRoles(roles);

		userRepository.save(user);
		return "User registered successfully!";
	}

	@Override
	public String login(LoginDto loginDto) {

		Authentication authentication = authenticateManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtTokenProvider.generateToken(authentication);

		return token;
	}

	public Role findUserRole() {
		Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role not found"));
		return userRole;
	}
}
