package com.GoScrum.GoScrumApi.service.impl;

import com.GoScrum.GoScrumApi.entity.*;
import com.GoScrum.GoScrumApi.payload.CreateVoteDto;
import com.GoScrum.GoScrumApi.repository.QuestionRepository;
import com.GoScrum.GoScrumApi.repository.RoomRepository;
import com.GoScrum.GoScrumApi.repository.VoteRepository;
import com.GoScrum.GoScrumApi.service.AuthService;
import com.GoScrum.GoScrumApi.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

	private final ModelMapper modelMapper;
	private final AuthService authService;
	private final VoteRepository voteRepository;
	private final RoomRepository roomRepository;
	private final QuestionRepository questionRepository;

	@Override
	public CreateVoteDto createVote(CreateVoteDto createVoteDto) {
		Vote vote = modelMapper.map(createVoteDto, Vote.class);

		Room room = roomRepository.findById(createVoteDto.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));

		Question question = questionRepository.findById(createVoteDto.getQuestionId()).orElseThrow(() -> new RuntimeException("Question not found"));

		User currentUser = authService.getCurrentUser();

		Optional<Vote> existingVoteOpt = voteRepository.findByUserIdAndQuestionIdAndRoomId(currentUser.getId(), createVoteDto.getQuestionId(), createVoteDto.getRoomId());

		System.out.println("existingVote: =========" + existingVoteOpt);

		if (existingVoteOpt.isPresent()) {
			Vote existingVote = existingVoteOpt.get();
			existingVote.setStatus(VotingStatus.UPDATED);
			existingVote.setScore(createVoteDto.getScore());
			Vote updatedVote = voteRepository.save(existingVote);

			return modelMapper.map(updatedVote, CreateVoteDto.class);
		} else {
			vote.setUser(currentUser);
			vote.setStatus(VotingStatus.COMPLETED);
			vote.setQuestion(question);
			vote.setRoom(room);

			Vote newVote = voteRepository.save(vote);

			return modelMapper.map(newVote, CreateVoteDto.class);
		}
	}
}
