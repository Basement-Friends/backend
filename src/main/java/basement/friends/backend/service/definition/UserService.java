package basement.friends.backend.service.definition;

import basement.friends.backend.model.User;

import java.util.List;

public interface UserService {
    User getById(String id);

    User getByUsername(String username);

    User getByEmail(String email);

    List<User> getAll();

    User save(User user);

    void delete(String id);

    boolean existsByEmail(String email);
}
