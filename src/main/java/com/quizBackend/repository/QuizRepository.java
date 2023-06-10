package com.quizBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quizBackend.entity.Question;

public interface QuizRepository extends JpaRepository<Question, Integer> {

	@Query(value = "SELECT * FROM quizbackend.questions where id = ?1", nativeQuery = true)
    Question checkRecord(@Param("id")long id);

	@Query(value = "SELECT * FROM quizbackend.questions WHERE id > ?1 ORDER BY id LIMIT 1", nativeQuery = true)
    Question next(@Param("id")long id);
}
