package basement.friends.backend.api;

import basement.friends.backend.auth.AuthService;
import basement.friends.backend.model.DTO.request.BasicUserRequest;
import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.model.User;
import basement.friends.backend.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all")
    List<User> getAllStudents(@RequestParam Map<String, String> filters) {
        return userService.getAll();
    }

    @Secured("ROLE_USER")
    @GetMapping("/{username}")
    Optional<User> getStudentByUsername(@PathVariable String username) {
        return Optional.ofNullable(userService.getByUsername(username));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/import")
    public ResponseEntity<EntityResponse> importUsers(@RequestBody Set<BasicUserRequest> requests) {
        return ResponseEntity.accepted()
                .body(EntityResponse.builder()
                        .message(authService.importUsers(requests))
                        .build());
    }

}
