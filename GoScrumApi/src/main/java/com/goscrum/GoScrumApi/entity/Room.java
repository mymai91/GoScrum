package com.GoScrum.GoScrumApi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
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

	// One room belongs to one host
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "host_id")
	private User host;

	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "users_rooms",
			joinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private Set<User> users = new HashSet<>();

	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Question> questions = new HashSet<>();

	// Room and Vote association: one room has many votes
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Vote> votes = new HashSet<>();
}
