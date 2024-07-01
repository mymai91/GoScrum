package com.GoScrum.GoScrumApi.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RoomDto {
	private long uuid;

	private String name;

	private String slug;

	private String description;

	@NotEmpty(message = "isActive status is required")
	private boolean isActive;

	@NotEmpty(message = "I=isPrivate status is required")
	private boolean isPrivate;

	private String password;


	@NotEmpty(message = "Host id is required")
	private Long hostId;
}
