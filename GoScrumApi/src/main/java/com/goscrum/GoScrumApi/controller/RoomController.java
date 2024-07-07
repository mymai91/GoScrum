package com.GoScrum.GoScrumApi.controller;

import com.GoScrum.GoScrumApi.payload.CreateRoomDto;
import com.GoScrum.GoScrumApi.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {

	// private RoomService roomService;
	//
	// public RoomController(RoomService roomService) {
	// 	this.roomService = roomService;
	// }

	private final RoomService roomService;

	@PostMapping("/create")
	@PreAuthorize("hasRole('ROLE_USER')")
	// TODO @Valid is not working
	// No validator could be found for constraint 'jakarta.validation.constraints.NotEmpty' validating type
	public ResponseEntity<CreateRoomDto> createRoom(@Valid @RequestBody CreateRoomDto createRoomDto) {
		CreateRoomDto room = roomService.createRoom(createRoomDto);
		return new ResponseEntity<>(room, HttpStatus.CREATED);
	}
}
