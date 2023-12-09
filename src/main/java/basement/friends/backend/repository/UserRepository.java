package basement.friends.backend.repository;

import basement.friends.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserByEmail(String email);

}
