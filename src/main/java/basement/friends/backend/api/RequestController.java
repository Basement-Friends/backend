package basement.friends.backend.api;

import basement.friends.backend.model.Chat;
import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.model.DTO.response.RequestResponse;
import basement.friends.backend.model.Request;
import basement.friends.backend.model.User;
import basement.friends.backend.model.enums.RequestStatus;
import basement.friends.backend.service.definition.ChatService;
import basement.friends.backend.service.definition.RequestService;
import basement.friends.backend.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/request")
public class RequestController {

    private final UserService userService;

    private final RequestService requestService;

    private final ChatService chatService;

    @PreAuthorize("hasAuthority({'ROLE_USER'})")
    @PostMapping("/{username}")
    public ResponseEntity<EntityResponse> createFriendRequest(@PathVariable String username) {
        User user = userService.getByUsername(username);
        User loggedUser = userService.getLoggedUser();
        Request friendRequest = Request.builder()
                .creationDate(new Date())
                .userId(user.getId())
                .updateDate(new Date())
                .initiator(loggedUser)
                .status(RequestStatus.PENDING)
                .build();
        requestService.saveRequest(friendRequest);
        return ResponseEntity.ok()
                .body(EntityResponse.builder()
                        .message("Friend request was send")
                        .build());
    }

    @PreAuthorize("hasAuthority({'ROLE_USER'})")
    @PostMapping("/accept/{id}")
    public ResponseEntity<EntityResponse> acceptFriendRequest(@PathVariable String id) {
        Request request = Request.builder().status(RequestStatus.ACCEPTED).build();
        User loggedUser = userService.getLoggedUser();
        requestService.updateRequest(id, request);
        chatService.createChat(Chat.builder()
                .users(new HashSet<>(Set.of(loggedUser, request.getInitiator())))
                .build());
        return ResponseEntity.ok()
                .body(EntityResponse.builder()
                        .message("Friend request was accepted")
                        .build());
    }

    @PreAuthorize("hasAuthority({'ROLE_USER'})")
    @PostMapping("/reject/{id}")
    public ResponseEntity<EntityResponse> rejectFriendRequest(@PathVariable String id) {
        Request request = Request.builder().status(RequestStatus.REJECTED).build();
        requestService.updateRequest(id, request);
        return ResponseEntity.ok()
                .body(EntityResponse.builder()
                        .message("Friend request was rejected")
                        .build());
    }

    @PreAuthorize("hasAuthority({'ROLE_ADMIN'})")
    @GetMapping("/{username}")
    public ResponseEntity<Set<RequestResponse>> getALlForSpecificUser(@PathVariable String username) {
        User user = userService.getByUsername(username);
        return ResponseEntity.ok()
                .body(requestService.getAllUserRequests(user).stream().map(
                        req -> RequestResponse.builder()
                        .from(userService.getExtendedUserInfo(req.getInitiator().getId()).getFirstName() + userService.getExtendedUserInfo(req.getInitiator().getId()).getLastName())
                        .type("Friend request")
                        .creationDate(req.getCreationDate())
                        .updateDate(req.getUpdateDate())
                        .build()
                ).collect(Collectors.toSet()));
    }

    @PreAuthorize("hasAuthority({'ROLE_USER'})")
    @GetMapping("/myRequests")
    public ResponseEntity<Set<RequestResponse>> getMyRequests() {
        User user = userService.getLoggedUser();
        return ResponseEntity.ok()
                .body(requestService.getAllUserRequests(user).stream().map(
                        req -> RequestResponse.builder()
                                .from(userService.getExtendedUserInfo(req.getInitiator().getId()).getFirstName() + userService.getExtendedUserInfo(req.getInitiator().getId()).getLastName())
                                .type("Friend request")
                                .creationDate(req.getCreationDate())
                                .updateDate(req.getUpdateDate())
                                .build()
                ).collect(Collectors.toSet()));
    }

    @PreAuthorize("hasAuthority({'ROLE_ADMIN'})")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EntityResponse> deleteRequest(@PathVariable String id) {
        requestService.deleteRequest(id);
        return ResponseEntity.ok()
                .body(EntityResponse.builder()
                        .message("Friend request was deleted")
                        .build());
    }


}
