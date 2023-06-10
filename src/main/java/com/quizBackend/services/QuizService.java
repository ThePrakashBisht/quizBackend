package com.quizBackend.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quizBackend.entity.Question;

public interface QuizService {

	public String fetchAndSaveQuiz() throws JsonProcessingException;    // fetch 5 random questions
	
	public String getQuestion(long id);   // return question and question_ID
	
	public String getAnswerAndNextQuiz(long id, String answer);  // takes question_ID and Answer  
	
	                  												// Returns correct Answer, Next question, Question_ID
}
