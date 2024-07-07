package com.GoScrum.GoScrumApi.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
// @Builder
public class CreateRoomDto {
	private long uuid;

	private String name;

	private String slug;

	// @Size(max = 150, message = "Description must be less than 150 characters")
	private String description;

	@JsonProperty("isActive")
	private boolean isActive;

	@JsonProperty("isPrivate")
	private boolean isPrivate;

	private String password;

	@NotNull(message = "Host id is required")
	private Long hostId;
}
