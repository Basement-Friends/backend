package basement.friends.backend.auth;


import basement.friends.backend.exception.EmailExistsException;
import basement.friends.backend.exception.IncorrectPasswordException;
import basement.friends.backend.exception.UsernameAlreadyTakenException;
import basement.friends.backend.exception.UsernameNotFoundException;
import basement.friends.backend.model.GamerInformation;
import basement.friends.backend.model.User;
import basement.friends.backend.model.enums.Role;
import basement.friends.backend.repository.GamerRepository;
import basement.friends.backend.repository.UserRepository;
import basement.friends.backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final GamerRepository gamerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailExistsException();
        }
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyTakenException();
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(Role.ROLE_USER))
                .build();
        userRepository.save(user);
        GamerInformation gamerInformation = GamerInformation.builder()
                .id(user.getId())
                .firstName(request.getFirstName())
                .lastName(request.getFirstName())
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
}
