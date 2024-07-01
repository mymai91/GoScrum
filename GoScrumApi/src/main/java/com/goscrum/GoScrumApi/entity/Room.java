package com.GoScrum.GoScrumApi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "rooms", uniqueConstraints = {@UniqueConstraint(columnNames = {"slug"})})
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "slug", nullable = false)
	private String slug;

	@Column(name = "description")
	private String description;

	@Column(name = "is_active", nullable = false)
	private boolean isActive = true;

	@Column(name = "is_private", nullable = false)
	private boolean isPrivate = false;

	@Column(name = "password")
	private String password;

	@OneToOne
	@JoinColumn(name = "host_id", nullable = false)
	private User host;
	
}
