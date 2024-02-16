package engine.persistancelayer;

import engine.businesslayer.User.User;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);
}
