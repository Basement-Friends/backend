package basement.friends.backend.api;

import basement.friends.backend.model.User;
import basement.friends.backend.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/all")
    List<User> getAllStudents(@RequestParam Map<String, String> filters) {
        return userService.getAll();
    }

    @Secured("ROLE_USER")
    @GetMapping("/{username}")
    Optional<User> getStudentByUsername(@PathVariable String username) {
        return Optional.ofNullable(userService.getByUsername(username));
    }

}
