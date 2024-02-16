package engine.businesslayer.Quiz;

import engine.businesslayer.Quiz.Quiz;
import engine.businesslayer.Quiz.QuizResponse;
import engine.businesslayer.User.UserAdapter;
import engine.exceptions.QuizNotFoundException;
import engine.exceptions.UnauthorizedQuizDeleteException;
import engine.persistancelayer.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service

public class QuizService {
    QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz getQuizz(Long id) {
        return quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException("Quiz with id " + id + " not found"));
    }
    public QuizResponse checkAnswer(Long id, Answer answer) {
        QuizResponse quizResponse = new QuizResponse();
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException("Quiz with id " + id + " not found"));
        if (quiz == null) {
            throw new QuizNotFoundException("Quiz with id " + id + " not found");
        } else {
            if (answer.getAnswers().equals(quiz.getAnswers())) {
                quizResponse.setSuccess(true);
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
        if(quiz.getUser().getId().equals(userAdapter.getUser().getId())) {
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
