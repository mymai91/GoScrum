package com.GoScrum.GoScrumApi.controller;

import com.GoScrum.GoScrumApi.payload.CreateVoteDto;
import com.GoScrum.GoScrumApi.service.VoteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votes")
public class VoteController {

	private final VoteService voteService;

	@PostMapping("/create")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String createVote(@Valid @RequestBody CreateVoteDto createVoteDto) {
		CreateVoteDto vote = voteService.createVote(createVoteDto);
		return "Vote is created successfully";
	}
}
