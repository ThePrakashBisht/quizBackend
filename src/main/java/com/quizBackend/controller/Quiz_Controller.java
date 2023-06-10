package com.quizBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.quizBackend.repository.QuizRepository;
import com.quizBackend.services.QuizService;

@RestController
public class Quiz_Controller {
	
	QuizRepository quizRepository;
	
	@Autowired
	private QuizService quizService;

	@GetMapping("/home")
	public String check() {
		return "hello bhai";
	}
	
	
	@GetMapping("/quiz")
	public String getQuestions() throws JsonProcessingException {
		quizService.fetchAndSaveQuiz();
		return "controller me hu";
	}
	
	long id = 173475;      //for getting first record
	
	@GetMapping("/play")
	public String getQuestion()  {
		return quizService.getQuestion(id);
	}

	
//	@PostMapping("/next")
//	public String getNextQuestion(@PathVariable long id , String answer)  {
//		
//		return quizService.getAnswerAndNextQuiz(id, answer);
//	}
	
	
	@RequestMapping(value="/next" , method = RequestMethod.POST)
			@ResponseBody
      public String getNextQuestion(@RequestParam("id") long id , @RequestParam("answer") String answer)  {
		
		return quizService.getAnswerAndNextQuiz(id, answer);
	}
}
