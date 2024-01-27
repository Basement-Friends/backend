package basement.friends.backend.api;

import basement.friends.backend.auth.AuthService;
import basement.friends.backend.model.DTO.request.BasicUserRequest;
import basement.friends.backend.model.DTO.request.ChangePasswordRequest;
import basement.friends.backend.model.DTO.request.GamerInformationRequest;
import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.model.User;
import basement.friends.backend.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;
    private final AuthService authService;


    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/all")
    List<User> getAllUsers(@RequestParam Map<String, String> filters) {
        return userService.getAll();
    }

    @PreAuthorize("hasAuthority({'ROLE_ADMIN'})")
    @GetMapping("/{username}")
    Optional<User> getUserByUsername(@PathVariable String username) {
        return Optional.ofNullable(userService.getByUsername(username));
    }

    @PreAuthorize("hasAuthority({'ROLE_USER'})")
    @GetMapping()
    Optional<User> getLoggedUser() {
        return Optional.ofNullable(userService.getLoggedUser());
    }

    @PreAuthorize("hasAuthority({'ROLE_ADMIN'})")
    @PostMapping("/import")
    public ResponseEntity<EntityResponse> importUsers(@RequestBody Set<BasicUserRequest> requests) {
        return ResponseEntity.accepted()
                .body(EntityResponse.builder()
                        .message(authService.importUsers(requests))
                        .build());
    }

    @PreAuthorize("hasAuthority({'ROLE_ADMIN'})")
    @PutMapping("/{username}/update")
    void updateUserByUsername(@PathVariable String username, @RequestBody GamerInformationRequest informationRequest) {

    }

    @PreAuthorize("hasAuthority({'ROLE_ADMIN'})")
    @DeleteMapping("/{username}")
    void deleteUserByUsername(@PathVariable String username) {
        userService.deleteByUsername(username);
    }

    //TODO
    @PreAuthorize("permitAll()")
    @PutMapping("/changePassword")
    public ResponseEntity<EntityResponse> changePassword(@RequestBody ChangePasswordRequest passwordRequest) {
        userService.changePassword(userService.getLoggedUser().getUsername(), passwordRequest);
        return ResponseEntity.accepted()
                .body(EntityResponse.builder()
                        .message("Password was successfully changed")
                        .build());
    }

}
