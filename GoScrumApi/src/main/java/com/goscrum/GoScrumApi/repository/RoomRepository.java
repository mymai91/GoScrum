package com.GoScrum.GoScrumApi.repository;

import com.GoScrum.GoScrumApi.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
	Optional<Room> findBySlug(String slug);
}
