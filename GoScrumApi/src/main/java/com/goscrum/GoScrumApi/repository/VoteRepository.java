package com.GoScrum.GoScrumApi.repository;

import com.GoScrum.GoScrumApi.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	Optional<Vote> findByUserIdAndQuestionIdAndRoomId(Long userId, UUID questionId, UUID roomId);
}