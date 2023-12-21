package engine.businesslayer;

import engine.exceptions.QuizNotFoundException;
import engine.persistancelayer.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class QuestionService {
    QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question getQuestion(int id) {
        return questionRepository.findById(id);
    }
    public QuestionResponse checkAnswer(int id, int answer) {
        QuestionResponse questionResponse = new QuestionResponse();
        Question question = questionRepository.findById(id);
        if (question == null) {
            throw new QuizNotFoundException("Quiz with id " + id + " not found");
        } else {
            if (answer == question.getAnswer()) {
                questionResponse.setSuccess(true);
                questionResponse.setFeedback("Congratulations, you're right!");
            } else {
                questionResponse.setSuccess(false);
                questionResponse.setFeedback("Wrong answer! Please, try again.");
            }
        }
        return questionResponse;
    }
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public List<Question> getQuestions() {
        return questionRepository.getAllQuestions();
    }
}
