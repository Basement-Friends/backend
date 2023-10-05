package basement.friends.backend.service.definition;

import basement.friends.backend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getById(String id);

    Optional<User> getByUsername(String username);

    List<User> getAll();

    User save(User user);

    void delete(String id);

    boolean existsByEmail(String email);
}
