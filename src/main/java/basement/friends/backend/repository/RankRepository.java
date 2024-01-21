package basement.friends.backend.repository;

import basement.friends.backend.model.Rank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RankRepository extends MongoRepository<Rank, String> {

    Optional<Rank> getByName(String name);

    boolean existsByNameIgnoreCase(String name);
}
