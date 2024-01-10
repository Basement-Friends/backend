package basement.friends.backend.repository;

import basement.friends.backend.model.Request;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

public interface RequestRepository extends MongoRepository<Request, String> {

    Set<Request> getAllByUserId(String userId);
}
