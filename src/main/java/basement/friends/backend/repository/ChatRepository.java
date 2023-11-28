package basement.friends.backend.repository;

import basement.friends.backend.model.Chat;
import basement.friends.backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Set;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
    Set<Chat> getChatsByUsersIn(Collection<Set<User>> users);
}
