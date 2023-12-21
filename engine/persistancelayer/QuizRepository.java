package engine.persistancelayer;

import engine.businesslayer.Quiz;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuizRepository {
    private final List<Quiz> quizzes;
    public QuizRepository() {
        quizzes = new ArrayList<>();
    }
    public Quiz findById(int id) {
        return id <= quizzes.size() && id > 0 ? quizzes.get(id - 1) : null;
    }
    public Quiz save(Quiz quiz) {
        quizzes.add(quiz);
        return quiz;
    }
    public List<Quiz> getAllQuizzes() {
        return quizzes;
    }
}
