package com.GoScrum.GoScrumApi.service.impl;

import com.GoScrum.GoScrumApi.entity.Question;
import com.GoScrum.GoScrumApi.entity.Room;
import com.GoScrum.GoScrumApi.payload.CreateQuestionDto;
import com.GoScrum.GoScrumApi.repository.QuestionRepository;
import com.GoScrum.GoScrumApi.repository.RoomRepository;
import com.GoScrum.GoScrumApi.service.AuthService;
import com.GoScrum.GoScrumApi.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

	private final ModelMapper modelMapper;
	private final AuthService authService;
	private final RoomRepository roomRepository;
	private final QuestionRepository questionRepository;

	@Override
	public CreateQuestionDto createQuestion(CreateQuestionDto createQuestionDto) {
		Room room = roomRepository.findById(createQuestionDto.getRoomId()).orElseThrow(() -> new RuntimeException("Question not found"));
		// getHost
		Long hostId = room.getHost().getId();
		Long currentUserId = authService.getCurrentUser().getId();

		if (hostId != currentUserId) {
			throw new RuntimeException("No permission");
		}

		Question question = modelMapper.map(createQuestionDto, Question.class);

		Question newQuestion = questionRepository.save(question);

		return modelMapper.map(newQuestion, CreateQuestionDto.class);
	}
}
