package basement.friends.backend.repository;

import basement.friends.backend.model.Gamer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamerRepository extends MongoRepository<Gamer, String> {
    Optional<Gamer> getGamerByNickName(String nickname);
}
