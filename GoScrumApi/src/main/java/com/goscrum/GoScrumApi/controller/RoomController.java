package com.GoScrum.GoScrumApi.controller;

import com.GoScrum.GoScrumApi.payload.RoomDto;
import com.GoScrum.GoScrumApi.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

	private RoomService roomService;

	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@PostMapping("/create")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<RoomDto> createRoom(@RequestBody RoomDto roomDto) {
		RoomDto room = roomService.createRoom(roomDto);
		return new ResponseEntity<>(room, HttpStatus.CREATED);
	}
}
