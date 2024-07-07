package com.GoScrum.GoScrumApi.service.impl;

import com.GoScrum.GoScrumApi.entity.Role;
import com.GoScrum.GoScrumApi.entity.User;
import com.GoScrum.GoScrumApi.payload.LoginDto;
import com.GoScrum.GoScrumApi.payload.RegisterDto;
import com.GoScrum.GoScrumApi.repository.RoleRepository;
import com.GoScrum.GoScrumApi.repository.UserRepository;
import com.GoScrum.GoScrumApi.security.JwtTokenProvider;
import com.GoScrum.GoScrumApi.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	// @Autowired
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticateManager;

	private final JwtTokenProvider jwtTokenProvider;


	// public AuthServiceImpl(UserRepository userRepository,
	//                        RoleRepository roleRepository,
	//                        PasswordEncoder passwordEncoder,
	//                        AuthenticationManager authenticateManager,
	//                        JwtTokenProvider jwtTokenProvider) {
	// 	this.userRepository = userRepository;
	// 	this.roleRepository = roleRepository;
	// 	this.passwordEncoder = passwordEncoder;
	// 	this.authenticateManager = authenticateManager;
	// 	this.jwtTokenProvider = jwtTokenProvider;
	// }

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

	@Override
	public User getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String)) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof User) {
				return (User) principal;
			} else if (principal instanceof UserDetails) {
				String username = ((UserDetails) principal).getUsername();
				return userRepository.findByUsername(username).orElse(null);
			}
		}
		return null;
	}

	// public User getCurrentUser() {
	// 	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	// 	if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
	// 		return (User) authentication.getPrincipal();
	// 	}
	//
	// 	return null;
	// }

	public Role findUserRole() {
		Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("Role not found"));
		return userRole;
	}

	private UserDetails getCurrentUserDetail() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
			return (UserDetails) authentication.getPrincipal();
		}

		return null;
	}
}
