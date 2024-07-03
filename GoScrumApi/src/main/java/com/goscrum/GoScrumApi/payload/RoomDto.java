package com.GoScrum.GoScrumApi.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
public class RoomDto {
	private long uuid;

	private String name;

	private String slug;

	private String description;

	@JsonProperty("isActive")
	private boolean isActive;

	@JsonProperty("isPrivate")
	private boolean isPrivate;

	private String password;


	@NotEmpty(message = "Host id is required")
	private Long hostId;
}
