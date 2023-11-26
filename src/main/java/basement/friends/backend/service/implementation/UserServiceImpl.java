package basement.friends.backend.service.implementation;

import basement.friends.backend.exception.EmailNotFoundException;
import basement.friends.backend.exception.UserIdNotFoundException;
import basement.friends.backend.exception.UsernameNotFoundException;
import basement.friends.backend.model.User;
import basement.friends.backend.repository.UserRepository;
import basement.friends.backend.service.definition.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    public User getById(String id) {
        return userRepository.findById(id)
                .orElseThrow(UserIdNotFoundException::new);
    }

    @Override
    public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return this.getByUsername(username);
        }
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getUserByUsername(username)
                .orElseThrow(UsernameNotFoundException::new);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getUserByEmail(email)
                .orElseThrow(EmailNotFoundException::new);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
