package com.quizBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.quizBackend.entity.Question;
import com.quizBackend.repository.QuizRepository;
import jakarta.transaction.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

@Transactional
@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Override
	public String fetchAndSaveQuiz() throws JsonProcessingException {

		  String uri = "https://jservice.io/api/random";
		  RestTemplate restTemplate = new RestTemplate();

		 ResponseEntity<Question[]> responseEntity =  restTemplate.getForEntity(uri, Question[].class); 
		 Question[] userArray = responseEntity.getBody();

	      ObjectMapper mapper = new ObjectMapper();
	      String jsonStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userArray);
	      jsonStr = jsonStr.replace("[", "").replace("]", "");
	      
	      Question quiz = mapper.readValue(jsonStr, Question.class);
	      
	      quizRepository.save(quiz);
	      return "Question saved";
	}

	
	@Override
	public String getQuestion(long id) {
		
		id = quizRepository.findById((int) id).get().getId();
		String question = quizRepository.findById((int) id).get().getQuestion();
		String str = "{\"questio\":"+id+",\"question\":\""+question+"\"}";
		return str ;
	}


	@Override
	public String getAnswerAndNextQuiz(long id, String answer) {
		
		if(quizRepository.checkRecord(id) != null) {
			answer = quizRepository.findById((int) id).get().getAnswer();
			
			if(quizRepository.next(id) != null) {
				int tempId = quizRepository.next(id).getId();
				String question = quizRepository.next(id).getQuestion();
				return "{\"correct_answer\":\""+answer+"\",\"next_question\":{\"question_id\":"+tempId+",\"question\":\""+question+"\"}}";
			}else {
				return "{\"correct_answer\":\""+answer+"\",\"next_question\":{\"question_id\":null,\"question\":\"null\"}}";		
			}
		}else {
			return "Not a valid id";
		}
		
}

}
