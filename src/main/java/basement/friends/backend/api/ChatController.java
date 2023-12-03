package basement.friends.backend.api;

import basement.friends.backend.model.Chat;
import basement.friends.backend.model.DTO.request.ChatRequest;
import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.service.definition.ChatFactory;
import basement.friends.backend.service.definition.ChatService;
import basement.friends.backend.service.definition.UserService;
import basement.friends.backend.service.implementation.ChatFactoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/chat")
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;

    @Secured({"ROLE_USER"})
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



}
