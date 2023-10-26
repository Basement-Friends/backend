package basement.friends.backend.repository;

import basement.friends.backend.model.GamerInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamerRepository extends MongoRepository<GamerInformation, String> {
}
