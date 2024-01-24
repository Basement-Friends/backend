package basement.friends.backend.service.implementation;

import basement.friends.backend.exception.EmailNotFoundException;
import basement.friends.backend.exception.UserIdNotFoundException;
import basement.friends.backend.exception.UsernameNotFoundException;
import basement.friends.backend.model.DTO.request.ChangePasswordRequest;
import basement.friends.backend.model.GamerInformation;
import basement.friends.backend.model.Picture;
import basement.friends.backend.model.User;
import basement.friends.backend.repository.GamerRepository;
import basement.friends.backend.repository.PictureRepository;
import basement.friends.backend.repository.RankRepository;
import basement.friends.backend.repository.UserRepository;
import basement.friends.backend.service.definition.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GamerRepository extendedUserRepository;
    private final PictureRepository pictureRepository;
    private final PasswordEncoder passwordEncoder;
    private final RankRepository rankRepository;

    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElseThrow(UserIdNotFoundException::new);
    }

    @Override
    public User getLoggedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return this.getByUsername(username);
        }
        return null;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getUserByUsername(username).orElseThrow(UsernameNotFoundException::new);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getUserByEmail(email).orElseThrow(EmailNotFoundException::new);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Set<User> getUsersByUsernames(Set<String> usernames) {
        Set<User> selectedUsers = new HashSet<>();
        List<String> failedUsernames = new ArrayList<>();
        usernames.forEach(username -> {
            userRepository.getUserByUsername(username).ifPresentOrElse(selectedUsers::add, () -> failedUsernames.add(username));
        });
        if (failedUsernames.isEmpty()) {
            return selectedUsers;
        } else {
            String joinedUsernames = String.join(", ", failedUsernames);
            throw new UserIdNotFoundException(STR. "Users: \{ joinedUsernames } are not found!" );
        }
    }

    @Override
    public Set<GamerInformation> getGamerByUsernames(Set<String> usernames) {
        Set<GamerInformation> selectedUsers = new HashSet<>();
        List<String> failedUsernames = new ArrayList<>();
        usernames.forEach(username -> {
            extendedUserRepository.getGamerInformationByNickName(username).ifPresentOrElse(selectedUsers::add, () -> failedUsernames.add(username));
        });
        if (failedUsernames.isEmpty()) {
            return selectedUsers;
        } else {
            String joinedUsernames = String.join(", ", failedUsernames);
            throw new UserIdNotFoundException(STR. "Users: \{ joinedUsernames } are not found!" );
        }
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
    public void deleteByUsername(String username) {
        User user = userRepository.getUserByUsername(username).orElseThrow(UserIdNotFoundException::new);
        userRepository.delete(user);
        extendedUserRepository.deleteById(user.getId());
        Picture picture = pictureRepository.getByUser_Username(username).orElse(null);
        pictureRepository.delete(Objects.requireNonNull(picture));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    @Override
    public void changePassword(String username, ChangePasswordRequest passwordRequest) {
        //TODO implement password checkout
        User user = userRepository.getUserByUsername(username).orElseThrow(UserIdNotFoundException::new);
        user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
        userRepository.save(user);
    }


}
