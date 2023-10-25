package basement.friends.backend.repository;

import basement.friends.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GamerRepository extends MongoRepository<User, String> {
}
