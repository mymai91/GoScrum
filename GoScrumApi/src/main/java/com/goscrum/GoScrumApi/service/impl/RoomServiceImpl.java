package com.GoScrum.GoScrumApi.service.impl;

import com.GoScrum.GoScrumApi.entity.Room;
import com.GoScrum.GoScrumApi.payload.RoomDto;
import com.GoScrum.GoScrumApi.repository.RoomRepository;
import com.GoScrum.GoScrumApi.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
	private RoomRepository roomRepository;
	private ModelMapper modelMapper;

	public RoomServiceImpl(RoomRepository roomRepository, ModelMapper modelMapper) {
		this.roomRepository = roomRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public RoomDto createRoom(RoomDto roomDto) {
		Room room = modelMapper.map(roomDto, Room.class);
		Room newRoom = roomRepository.save(room);

		return modelMapper.map(newRoom, RoomDto.class);
	}
}
