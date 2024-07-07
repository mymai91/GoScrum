package com.GoScrum.GoScrumApi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

// @Getter
// @Setter
// @NoArgsConstructor
// @AllArgsConstructor

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
