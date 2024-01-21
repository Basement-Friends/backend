package basement.friends.backend.service.definition;

import basement.friends.backend.model.DTO.request.ChangePasswordRequest;
import basement.friends.backend.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User getById(String id);

    User getLoggedUser();

    User getByUsername(String username);

    User getByEmail(String email);

    List<User> getAll();

    Set<User> getUsersByUsernames(Set<String> usernames);

    User save(User user);

    void delete(String id);

    void deleteByUsername(String username);

    boolean existsByEmail(String email);

    void changePassword(String username, ChangePasswordRequest passwordRequest);


}
