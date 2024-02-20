package engine.persistancelayer;

import engine.businesslayer.Quiz.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuizRepository extends PagingAndSortingRepository<Quiz, Long>, CrudRepository<Quiz, Long> {
}
