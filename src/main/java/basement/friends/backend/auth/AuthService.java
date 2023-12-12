package basement.friends.backend.auth;


import basement.friends.backend.exception.EmailExistsException;
import basement.friends.backend.exception.IncorrectPasswordException;
import basement.friends.backend.exception.UsernameAlreadyTakenException;
import basement.friends.backend.exception.UsernameNotFoundException;
import basement.friends.backend.model.DTO.request.BasicUserRequest;
import basement.friends.backend.model.GamerInformation;
import basement.friends.backend.model.User;
import basement.friends.backend.repository.GamerRepository;
import basement.friends.backend.repository.UserRepository;
import basement.friends.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import static basement.friends.backend.model.enums.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final GamerRepository gamerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        verifyRequestCorrectness(request);
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(ROLE_USER))
                .build();
        userRepository.save(user);
        GamerInformation gamerInformation = GamerInformation.builder()
                .id(user.getId())
                .firstName(request.getFirstName())
                .lastName(request.getFirstName())
                .nickName(request.getNickname())
                .build();
        gamerRepository.save(gamerInformation);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = userRepository.getUserByUsername(request.getUsername())
                .orElseGet(() -> userRepository.getUserByEmail(request.getUsername())
                        .orElseThrow(UsernameNotFoundException::new));
        boolean decoded = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!decoded) {
            throw new IncorrectPasswordException();
        }
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    public String importUsers(Set<BasicUserRequest> requests) {
        Set<String> errorList = new HashSet<>();
        String msg = "";
        AtomicBoolean isOk = new AtomicBoolean(true);
        requests.forEach(req->{
            if (userRepository.existsByEmail(req.getEmail())) {
                errorList.add(STR."Account for email \{req.getEmail()} already exists");
                isOk.set(false);
            }
            if (userRepository.existsByUsername(req.getUsername())) {
                errorList.add(STR."Username \{req.getUsername()} is already taken");
                isOk.set(false);
            }
            if (isOk.get()) {
                User user = User.builder()
                        .roles(Collections.singleton(ROLE_USER))
                        .username(req.getUsername())
                        .email(req.getEmail())
                        .password(passwordEncoder.encode(req.getUsername()))
                        .build();
                GamerInformation gamer = GamerInformation.builder()
                        .nickName(req.getUsername())
                        .lastName(req.getLastname())
                        .firstName(req.getLastname())
                        .build();
                userRepository.save(user);
                gamerRepository.save(gamer);
                isOk.set(true);
            }
        });
        if (!errorList.isEmpty()) {
            for (String err: errorList) {
                msg = STR."\{msg} \n\{err}";
            }
        } else {
            msg = "All users were imported successfully";
        }
        return msg;
    }
    private void verifyRequestCorrectness(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailExistsException();
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyTakenException();
        }
    }
}
