package com.example.quizapp.service;

import com.example.quizapp.DTO.QuizQuestionDTO;
import com.example.quizapp.model.QuizQuestion;
import com.example.quizapp.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }

    public List<QuizQuestionDTO> getQuestionsForQuiz() {

        List<QuizQuestion> questions = quizRepository.findAll();
        List<QuizQuestionDTO> dtoList = new ArrayList<>();

        for (QuizQuestion q : questions) {

            List<String> options = new ArrayList<>();
            options.add(q.getOptionA());
            options.add(q.getOptionB());
            options.add(q.getOptionC());
            options.add(q.getOptionD());

            QuizQuestionDTO dto = new QuizQuestionDTO(
                    q.getId(),
                    q.getQuestion(),
                    options
            );

            dtoList.add(dto);
        }

        return dtoList;
    }


//
//    public List<Map<String, Object>> getQuestionsForQuiz() {
//
//        List<QuizQuestion> qQ = quizRepository.findAll();
//        List<Map<String, Object>> response = new java.util.ArrayList<>();
//
//        for (QuizQuestion quizQuestion : qQ) {
//            Map<String, Object> map = new HashMap<>();
//            map.put("id", quizQuestion.getId());
//            map.put("question", quizQuestion.getQuestion());
//
//            map.put("options", List.of(
//                    quizQuestion.getOptionA(),
//                    quizQuestion.getOptionB(),
//                    quizQuestion.getOptionC(),
//                    quizQuestion.getOptionD()
//            ));
//            response.add(map);
//        }
//        return response;
//    }
//
    public Map<String, Integer> submitAnwers(Map<Integer, String> answers){
        int score = 0;
        int total = answers.size();
         for (Map.Entry<Integer, String> answer : answers.entrySet()) {
             int questionId = answer.getKey();
             String userAnswer = answer.getValue();

             QuizQuestion quizQuestion = quizRepository.findById(questionId);

             if(quizQuestion != null && quizQuestion.getAnswer().equalsIgnoreCase(userAnswer)){
                 score++;
             }
         }
         Map<String, Integer> result = new HashMap<>();
         result.put("score", score);
         result.put("total", total);
         return result;
    }

    public void addQuestion(QuizQuestion quizQuestion){
        quizRepository.save(quizQuestion);
    }
}
