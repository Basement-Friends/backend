package basement.friends.backend.api;

import basement.friends.backend.model.Chat;
import basement.friends.backend.model.DTO.request.ChatRequest;
import basement.friends.backend.model.DTO.response.ChatResponse;
import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.model.DTO.response.UserBasicResponse;
import basement.friends.backend.model.User;
import basement.friends.backend.service.definition.ChatFactory;
import basement.friends.backend.service.definition.ChatService;
import basement.friends.backend.service.definition.UserService;
import basement.friends.backend.service.implementation.ChatFactoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/chat")
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;

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

        chats.forEach(chat-> {
            Set<UserBasicResponse> users = new HashSet<>();
            chat.getUsers().forEach(user-> users.add(
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
    @GetMapping("/message")
    public ResponseEntity<String> checkIfMessageIsCorrect() {
        try {

            String uri="http://localhost:12345/isToxic";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            RestTemplate restTemplate = new RestTemplate();

            String body = "{\"message\": \"some fikusny message\"}";

            ResponseEntity<String> result = restTemplate.postForEntity(uri, new HttpEntity<>(body, headers), String.class);

            return new ResponseEntity<>( result.getStatusCodeValue() == 200 ? "Mesagge is ok" : "Message is not ok", HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error!, Please try again");
        }
    }



}
