package com.GoScrum.GoScrumApi.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

// Database level
@Entity
@Table(name = "rooms", uniqueConstraints = {@UniqueConstraint(columnNames = {"slug"})})
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "slug", nullable = false, unique = true)
	private String slug;

	@Column(name = "description")
	private String description;

	@Column(name = "is_active", nullable = false)
	private boolean isActive = true;

	@Column(name = "is_private", nullable = false)
	private boolean isPrivate;

	@Column(name = "password", nullable = false)
	private String password;

	@OneToOne
	@JoinColumn(name = "host_id", nullable = false)
	private User host;

	@PrePersist
	private void onPrePersist() {
		handleDefaultPassword();
		handleDefaultSlug();
		handleDefaultStatus();
	}

	@PostConstruct
	private void onPostPersist() {

	}

	private void handleDefaultPassword() {
		if (isPrivate) {
			this.setPassword("12345");
			this.setPrivate(true);
		} else {
			this.setPrivate(false);
		}
	}

	private void handleDefaultStatus() {
		if (!isActive) {
			this.setActive(true);
		}
	}

	private void handleDefaultSlug() {
		if (slug == null || slug.isEmpty()) {
			this.setSlug(generateSlug());
		}
	}

	private String generateSlug() {
		return UUID.randomUUID().toString();
	}

}
