package basement.friends.backend.repository;

import basement.friends.backend.model.Picture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PictureRepository extends MongoRepository<Picture, String> {
    Optional<Picture> getByUser_Username(String username);
}
