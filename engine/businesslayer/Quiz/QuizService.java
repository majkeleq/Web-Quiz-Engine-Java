package engine.businesslayer.Quiz;

import engine.businesslayer.User.User;
import engine.businesslayer.User.UserAdapter;
import engine.exceptions.QuizNotFoundException;
import engine.persistancelayer.CompletionRepository;
import engine.persistancelayer.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service

public class QuizService {
    QuizRepository quizRepository;
    CompletionRepository completionsRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, CompletionRepository completionRepository) {
        this.quizRepository = quizRepository;
        this.completionsRepository = completionRepository;
    }

    public Quiz getQuizz(Long id) {
        return quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException("Quiz with id " + id + " not found"));
    }

    public QuizResponse checkAnswer(Long id, Answer answer, User user) {
        QuizResponse quizResponse = new QuizResponse();
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException("Quiz with id " + id + " not found"));
        if (quiz == null) {
            throw new QuizNotFoundException("Quiz with id " + id + " not found");
        } else {
            if (answer.getAnswers().equals(quiz.getAnswers())) {
                quizResponse.setSuccess(true);
                completionsRepository.save(new Completion(user, quiz));
                quizResponse.setFeedback("Congratulations, you're right!");
            } else {
                quizResponse.setSuccess(false);
                quizResponse.setFeedback("Wrong answer! Please, try again.");
            }
        }
        return quizResponse;
    }

    public Quiz save(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Page<Quiz> getQuizzes(Integer page) {
        return quizRepository.findAll(PageRequest.of(page, 10));
    }

    public QuizResponse deleteQuiz(Long id, UserAdapter userAdapter) {
        QuizResponse quizResponse = new QuizResponse();
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException("Quiz with id " + id + " not found"));
        if (quiz.getUser().getId().equals(userAdapter.getUser().getId())) {
            quizRepository.delete(quiz);
            quizResponse.setSuccess(true);
            quizResponse.setFeedback("Quiz deleted");
            return quizResponse;
        } else {
            quizResponse.setSuccess(false);
            quizResponse.setFeedback("Unauthorized");
            return quizResponse;
        }
    }
}
