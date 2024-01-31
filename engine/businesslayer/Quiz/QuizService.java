package engine.businesslayer.Quiz;

import engine.businesslayer.Quiz.Quiz;
import engine.businesslayer.Quiz.QuizResponse;
import engine.exceptions.QuizNotFoundException;
import engine.persistancelayer.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Iterable<Quiz> getQuizzes() {
        return quizRepository.findAll();
    }
}
