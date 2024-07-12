package com.GoScrum.GoScrumApi.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "title")
	private String title;

	@Column(name = "detail")
	private String detail;

	@Column(name = "is_active")
	private boolean isActive;

	@Column(name = "display_order")
	private int displayOrder;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;
}
