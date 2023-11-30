package basement.friends.backend.api;

import basement.friends.backend.model.DTO.request.ChatRequest;
import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.service.definition.ChatFactory;
import basement.friends.backend.service.definition.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/chat")
public class ChatController {
    private final ChatService chatService;
    private final ChatFactory chatFactory;

    @Secured({"ROLE_USER"})
    @PostMapping("/create")
    public ResponseEntity<EntityResponse> postChat(ChatRequest chatRequest) {
        return ResponseEntity.accepted()
                .body(EntityResponse.builder()
                        .message("Chat was created")
                        .build());

    }



}
