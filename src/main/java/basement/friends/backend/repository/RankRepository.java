package basement.friends.backend.repository;

import basement.friends.backend.model.Rank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankRepository extends MongoRepository<Rank, String> {

    Rank getByName(String name);
}
