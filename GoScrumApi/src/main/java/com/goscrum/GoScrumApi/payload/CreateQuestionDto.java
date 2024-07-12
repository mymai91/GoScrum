package com.GoScrum.GoScrumApi.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Data
// Builder have to go with @Builder @NoArgsConstructor @AllArgsConstructor
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateQuestionDto {
	@NotEmpty(message = "Title is required")
	@Size(min = 10, max = 150, message = "Title must be between 10 and 150 characters")
	private String title;

	@Size(max = 300, message = "Detail must be less than 300 characters")
	private String detail;

	@JsonProperty("isActive")
	private boolean isActive;

	@NotNull(message = "Display Order is required")
	private int displayOrder;

	@NotNull(message = "Room id is required")
	private UUID roomId;
}
