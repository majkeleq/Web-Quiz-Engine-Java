package engine.persistancelayer;

import engine.businesslayer.Quiz.Quiz;
import org.springframework.data.repository.CrudRepository;

public interface QuizRepository extends CrudRepository<Quiz, Long> {
}
