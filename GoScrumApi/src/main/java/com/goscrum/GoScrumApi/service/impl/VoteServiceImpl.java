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
		System.out.println("createVoteDto: ======" + createVoteDto);
		Vote vote = modelMapper.map(createVoteDto, Vote.class);

		Room room = roomRepository.findById(createVoteDto.getRoomId()).orElseThrow(() -> new RuntimeException("Room not found"));

		Question question = questionRepository.findById(createVoteDto.getQuestionId()).orElseThrow(() -> new RuntimeException("Question not found"));
		// check if user has already voted
		// if yes, update vote
		// if no, create vote


		User currentUser = authService.getCurrentUser();

		// check if user has already voted
		// Vote existingVote = voteRepository.findByUserAndQuestion(currentUser, question);
		// Optional<Vote> existingVote = voteRepository.findByUserAndAndQuestionAndRoom(currentUser.getId(), question.getId(), room.getId());
		//
		// if (existingVote.isPresent()) {
		// 	existingVote.get().setStatus(VotingStatus.valueOf("COMPLETED"));
		// 	existingVote.get().setScore(createVoteDto.getScore());
		//
		// 	Vote updatedVote = voteRepository.save(existingVote.get());
		// 	return modelMapper.map(updatedVote, CreateVoteDto.class);
		// }

		vote.setUser(currentUser);
		vote.setStatus(VotingStatus.valueOf(createVoteDto.getStatus()));
		vote.setQuestion(question);
		vote.setRoom(room);

		Vote newVote = voteRepository.save(vote);

		return modelMapper.map(newVote, CreateVoteDto.class);
	}
}
