package engine.businesslayer;

import engine.exceptions.QuizNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuizController {
    QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @PostMapping("/api/quizzes")
    ResponseEntity<Quiz> addQuizz(@RequestBody Quiz quiz) {

        return ResponseEntity.ok(quizService.save(quiz));
    }

    @GetMapping("/api/quizzes")
    ResponseEntity<List<Quiz>> getQuizzes() {
        return ResponseEntity.ok(quizService.getQuizzes());
    }

    @GetMapping("/api/quizzes/{id}")
    ResponseEntity<Quiz> getQuizz(@PathVariable int id) {
        Quiz quiz = quizService.getQuizz(id);
        if (quiz == null) {
            throw new QuizNotFoundException("Quiz with id = " + id + " not found");
        } else {
            return ResponseEntity.ok(quiz);
        }
    }

    @PostMapping("/api/quizzes/{id}/solve")
    ResponseEntity<QuizResponse> solveQuizz(@PathVariable int id, @RequestParam List<Integer> answer) {
        QuizResponse quizResponse = quizService.checkAnswer(id, answer);
        if (quizResponse == null) {
            throw new QuizNotFoundException("Quiz with id " + id + " not found");
        } else {
            return ResponseEntity.ok(quizResponse);
        }
    }
}
