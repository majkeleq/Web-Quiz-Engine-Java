package engine.businesslayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import engine.businesslayer.QuestionResponse;
import engine.businesslayer.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizController {
    QuestionService questionService;

    @Autowired
    public QuizController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @PostMapping("/api/quizzes")
    ResponseEntity<Question> addQuestion(@RequestBody Question question) {

        return ResponseEntity.ok(questionService.save(question));
    }

    @GetMapping("/api/quizzes")
    ResponseEntity<List<Question>> getQuestions() {
        List<Question> questions = questionService.getQuestions();
        return questions.isEmpty() ? ResponseEntity.notFound().build(): ResponseEntity.ok(questionService.getQuestions());
    }

    @GetMapping("/api/quizzes/{id}")
    ResponseEntity<Question> getQuestion(@PathVariable int id) {
        Question question = questionService.getQuestion(id);
        return question == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(question);
    }

    @PostMapping("/api/quizzes/{id}/solve")
    ResponseEntity<QuestionResponse> solveQuestion(@PathVariable int id, @RequestParam int answer) {
        QuestionResponse questionResponse = questionService.checkAnswer(id, answer);
        if (questionResponse == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(questionResponse);
        }
    }
}
