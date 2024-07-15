package com.GoScrum.GoScrumApi.controller;

import com.GoScrum.GoScrumApi.payload.CreateQuestionDto;
import com.GoScrum.GoScrumApi.service.QuestionService;
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
@RequestMapping("/api/questions")
public class QuestionController {

	private final QuestionService questionService;

	@PostMapping("/create")
	@PreAuthorize("hasRole('ROLE_USER')")
	public String createQuestion(@Valid @RequestBody CreateQuestionDto createQuestionDto) {
		CreateQuestionDto question = questionService.createQuestion(createQuestionDto);

		return "Question is created successfullyyyyy";
	}
}
