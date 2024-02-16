package engine.businesslayer.Quiz;

import engine.businesslayer.Quiz.Answer;
import engine.businesslayer.Quiz.Quiz;
import engine.businesslayer.Quiz.QuizResponse;
import engine.businesslayer.Quiz.QuizService;
import engine.businesslayer.User.UserAdapter;
import engine.exceptions.QuizNotFoundException;
import engine.exceptions.UnauthorizedQuizDeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    ResponseEntity<Quiz> addQuizz(@Valid @RequestBody Quiz quiz, @AuthenticationPrincipal UserAdapter userAdapter) {
        quiz.afterValidation();
        quiz.setUser(userAdapter.getUser());
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
        return ResponseEntity.ok(quizResponse);

    }

    @DeleteMapping("/api/quizzes/{id}")
    ResponseEntity<Void> deleteQuizz(@PathVariable Long id, @AuthenticationPrincipal UserAdapter userAdapter) {
        QuizResponse quizResponse = quizService.deleteQuiz(id, userAdapter);
        if (quizResponse.isSuccess()) {
            return ResponseEntity.noContent().build();
        } else {
            throw new UnauthorizedQuizDeleteException("Unauthorized Access");
        }
    }
}
