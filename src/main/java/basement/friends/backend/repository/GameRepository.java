package basement.friends.backend.repository;

import basement.friends.backend.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
    boolean existsByName(String gameName);
}
