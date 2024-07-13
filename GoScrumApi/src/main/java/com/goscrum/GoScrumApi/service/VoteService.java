package com.GoScrum.GoScrumApi.service;

import com.GoScrum.GoScrumApi.payload.CreateVoteDto;
import org.springframework.stereotype.Service;

public interface VoteService {
	CreateVoteDto createVote(CreateVoteDto createVoteDto);
}
