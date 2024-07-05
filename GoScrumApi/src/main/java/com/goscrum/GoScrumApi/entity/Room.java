package com.GoScrum.GoScrumApi.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor

// @Builder
@Data
@NoArgsConstructor

// Database level
@Entity
@Table(name = "rooms", uniqueConstraints = {@UniqueConstraint(columnNames = {"slug"})})
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "name")
	private String name;

	@Column(name = "slug")
	private String slug;

	@Column(name = "description")

	private String description;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "is_private")
	private boolean isPrivate;

	@Column(name = "password")
	private String password;

	// LAZY: Suitable for scenarios where related data is not always needed.
	// EAGER: Suitable for scenarios where related data is always required.
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "host_id")
	private User host;
}
