package engine.persistancelayer;

import engine.businesslayer.Question;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepository {
    private final List<Question> questions;
    public QuestionRepository() {
        questions = new ArrayList<>();
    }
    public Question findById(int id) {
        return id <= questions.size() && id > 0 ? questions.get(id - 1) : null;
    }
    public Question save(Question question) {
        questions.add(question);
        return question;
    }
}
