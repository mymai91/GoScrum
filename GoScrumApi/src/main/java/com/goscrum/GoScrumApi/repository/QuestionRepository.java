package com.GoScrum.GoScrumApi.repository;

import com.GoScrum.GoScrumApi.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
}
