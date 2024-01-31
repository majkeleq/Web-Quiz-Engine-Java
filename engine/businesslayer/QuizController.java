package engine.businesslayer;

import engine.businesslayer.Quiz.Answer;
import engine.businesslayer.Quiz.Quiz;
import engine.businesslayer.Quiz.QuizResponse;
import engine.businesslayer.Quiz.QuizService;
import engine.exceptions.QuizNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class QuizController {
    QuizService quizService;


    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }



    @PostMapping("/api/quizzes")
    ResponseEntity<Quiz> addQuizz(@Valid @RequestBody Quiz quiz) {
        quiz.afterValidation();
        return ResponseEntity.ok(quizService.save(quiz));
    }

    @GetMapping("/api/quizzes")
    ResponseEntity<Iterable<Quiz>> getQuizzes() {
        return ResponseEntity.ok(quizService.getQuizzes());
    }

    @GetMapping("/api/quizzes/{id}")
    ResponseEntity<Quiz> getQuizz(@PathVariable Long id) {
        Quiz quiz = quizService.getQuizz(id);
        if (quiz == null) {
            throw new QuizNotFoundException("Quiz with id = " + id + " not found");
        } else {
            return ResponseEntity.ok(quiz);
        }
    }

    @PostMapping("/api/quizzes/{id}/solve")
    ResponseEntity<QuizResponse> solveQuizz(@PathVariable Long id, @RequestBody Answer answer) {
        QuizResponse quizResponse = quizService.checkAnswer(id, answer);
        if (quizResponse == null) {
            throw new QuizNotFoundException("Quiz with id " + id + " not found");
        } else {
            return ResponseEntity.ok(quizResponse);
        }
    }
}
