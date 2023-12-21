package engine.businesslayer;

import engine.exceptions.QuizNotFoundException;
import engine.persistancelayer.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class QuizService {
    QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz getQuizz(int id) {
        return quizRepository.findById(id);
    }
    public QuizResponse checkAnswer(int id, int answer) {
        QuizResponse quizResponse = new QuizResponse();
        Quiz quiz = quizRepository.findById(id);
        if (quiz == null) {
            throw new QuizNotFoundException("Quiz with id " + id + " not found");
        } else {
            if (answer == quiz.getAnswer()) {
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

    public List<Quiz> getQuizzes() {
        return quizRepository.getAllQuizzes();
    }
}
