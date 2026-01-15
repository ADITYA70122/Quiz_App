package com.example.quizapp.controller;

import com.example.quizapp.DTO.QuizQuestionDTO;
import com.example.quizapp.model.QuizQuestion;
import com.example.quizapp.service.QuizService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/getAllQue")
    public ResponseEntity<List<QuizQuestionDTO>> getQuestions() {
        return ResponseEntity.ok(quizService.getQuestionsForQuiz());
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitQuestion(@RequestBody Map<Integer, String> answrers){
        return ResponseEntity.ok(quizService.submitAnwers(answrers));
    }

    @PostMapping("/addQuizQue")
    public ResponseEntity<String> addQuestion(@RequestBody QuizQuestion quizQuestion){
        quizService.addQuestion(quizQuestion);
        return ResponseEntity.ok("Question added");
    }
}
