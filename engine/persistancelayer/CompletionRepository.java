package engine.persistancelayer;

import engine.businesslayer.Quiz.Completion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompletionRepository extends PagingAndSortingRepository<Completion, Long>, CrudRepository<Completion, Long> {
}
