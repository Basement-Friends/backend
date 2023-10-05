package basement.friends.backend.service.implementation;

import basement.friends.backend.model.User;
import basement.friends.backend.repository.UserRepository;
import basement.friends.backend.service.definition.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    @Override
    public Optional<User> getById(String id) {
        return userRepository.findById(id).blockOptional();
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
