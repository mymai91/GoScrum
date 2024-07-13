package com.GoScrum.GoScrumApi.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVoteDto {
	private Long id;
	
	@NotNull(message = "Score is required")
	private int score;

	@NotNull(message = "Status is required")
	private String status;

	@NotNull(message = "Room id is required")
	private UUID roomId;

	@NotNull(message = "Question id is required")
	private UUID questionId;
}
