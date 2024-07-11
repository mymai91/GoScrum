package com.GoScrum.GoScrumApi.service.impl;

import com.GoScrum.GoScrumApi.entity.Room;
import com.GoScrum.GoScrumApi.entity.User;
import com.GoScrum.GoScrumApi.payload.CreateRoomDto;
import com.GoScrum.GoScrumApi.repository.RoomRepository;
import com.GoScrum.GoScrumApi.repository.UserRepository;
import com.GoScrum.GoScrumApi.service.AuthService;
import com.GoScrum.GoScrumApi.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
	// Without @RequiredArgsConstructor Define contructor
	// private RoomRepository roomRepository;
	// private ModelMapper modelMapper;

	// public RoomServiceImpl(RoomRepository roomRepository, ModelMapper modelMapper) {
	// 	this.roomRepository = roomRepository;
	// 	this.modelMapper = modelMapper;
	// }

	// With @RequiredArgsConstructor Dont need to define constructor, use `final` to ensure fields are immutable mand must be initialized when object is created

	private final RoomRepository roomRepository;
	private final ModelMapper modelMapper;
	private final AuthService authService;

	@Override
	public CreateRoomDto createRoom(CreateRoomDto createRoomDto) {
		User currentUser = authService.getCurrentUser();

		Room room = modelMapper.map(createRoomDto, Room.class);
		room.setSlug(generateUniqueSlug(room.getSlug()));
		room.setPassword(generateDefaultPassword(room.isPrivate()));
		room.setHost(currentUser);

		// Add the current user to the room's user set
		room.getUsers().add(currentUser);

		Room newRoom = roomRepository.save(room);

		return modelMapper.map(newRoom, CreateRoomDto.class);
	}

	private String generateUniqueSlug(String slug) {
		if (slug == null || slug.isEmpty()) {
			slug = UUID.randomUUID().toString();
		}
		while (roomRepository.existsBySlug(slug)) {
			slug = UUID.randomUUID().toString();
		}
		return slug;
	}

	private String generateDefaultPassword(boolean isPrivate) {

		Random random = new Random();
		int number = 100000 + random.nextInt(900000);

		if (isPrivate) {
			return String.valueOf(number);
		} else {
			return null;
		}
	}
}
