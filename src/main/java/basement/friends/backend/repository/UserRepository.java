package basement.friends.backend.repository;

import basement.friends.backend.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    boolean existsByEmail(String email);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserById(String id);

    Optional<User> getUserByEmail(String email);

    List<User> getAll();



}
