package basement.friends.backend.repository;

import basement.friends.backend.model.GamerInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GamerRepository extends MongoRepository<GamerInformation, String> {
    Optional<GamerInformation> getGamerInformationByNickName(String nickname);
}
