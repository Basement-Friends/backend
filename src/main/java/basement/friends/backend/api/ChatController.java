package basement.friends.backend.api;

import basement.friends.backend.integration.ToxicMessageIntegrator;
import basement.friends.backend.model.Chat;
import basement.friends.backend.model.DTO.request.ChatRequest;
import basement.friends.backend.model.DTO.request.MessageRequest;
import basement.friends.backend.model.DTO.response.ChatResponse;
import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.model.DTO.response.UserBasicResponse;
import basement.friends.backend.model.User;
import basement.friends.backend.service.definition.ChatFactory;
import basement.friends.backend.service.definition.ChatService;
import basement.friends.backend.service.definition.UserService;
import basement.friends.backend.service.implementation.ChatFactoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/chat")
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;
    private final ToxicMessageIntegrator messageIntegrator;

    @PreAuthorize("hasAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @PostMapping("/create")
    public ResponseEntity<EntityResponse> postChat(@RequestBody ChatRequest chatRequest) {
        ChatFactory chatFactory = new ChatFactoryImpl();
        Chat chat = chatFactory.createFromRequest(
                userService.getUsersByUsernames(
                        chatRequest.getUsernames()
                ));
        chatService.createChat(chat);
        return ResponseEntity.accepted()
                .body(EntityResponse.builder()
                        .message("Chat was created")
                        .build());

    }

    @PreAuthorize("hasAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @GetMapping("/myChats")
    public ResponseEntity<Set<ChatResponse>> getMyChats(@RequestBody ChatRequest chatRequest) {
        User loggedUser = userService.getLoggedUser();
        Set<Chat> chats = chatService.getByUsers(loggedUser);
        Set<ChatResponse> chatResponses = new HashSet<>();

        chats.forEach(chat -> {
            Set<UserBasicResponse> users = new HashSet<>();
            chat.getUsers().forEach(user -> users.add(
                    UserBasicResponse.builder()
                            .username(user.getUsername())
                            .build()
            ));
            chatResponses.add(ChatResponse.builder()
                    .name(null)
                    .users(users)
                    .messages(chat.getMessages())
                    .build());
        });
        return ResponseEntity.accepted()
                .body(chatResponses);

    }

    @PreAuthorize("hasAuthority({'ROLE_USER'})")
    @PostMapping("/message")
    public ResponseEntity<EntityResponse> sendMassage(@RequestBody MessageRequest messageRequest) {
        if (!messageIntegrator.isMessageToxic(messageRequest)) {
            return ResponseEntity.accepted()
                    .body(EntityResponse.builder()
                            .message("Message was send")
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body(EntityResponse.builder()
                            .message("Message possibly contains questionable content")
                            .build());
        }

    }


}
