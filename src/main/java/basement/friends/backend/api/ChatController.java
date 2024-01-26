package basement.friends.backend.api;

import basement.friends.backend.integration.ToxicMessageValidator;
import basement.friends.backend.model.Chat;
import basement.friends.backend.model.DTO.request.ChatRequest;
import basement.friends.backend.model.DTO.request.MessageRequest;
import basement.friends.backend.model.DTO.response.ChatResponse;
import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.model.DTO.response.UserBasicResponse;
import basement.friends.backend.model.GamerInformation;
import basement.friends.backend.model.Message;
import basement.friends.backend.service.definition.ChatFactory;
import basement.friends.backend.service.definition.ChatService;
import basement.friends.backend.service.definition.UserService;
import basement.friends.backend.service.implementation.ChatFactoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static basement.friends.backend.model.DTO.response.ChatResponse.generateName;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/chat")
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;
    private final ToxicMessageValidator messageIntegrator;

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<EntityResponse> postChat(@RequestBody ChatRequest chatRequest) {
        ChatFactory chatFactory = new ChatFactoryImpl();
        Chat chat = chatFactory.createFromRequest(userService.getGamersByUsernames(chatRequest.getUsernames()));
        chatService.createChat(chat);
        return ResponseEntity.accepted().body(EntityResponse.builder().message("Chat was created").build());

    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/myChats")
    public ResponseEntity<Set<ChatResponse>> getMyChats() {
        GamerInformation loggedUser = userService.getGamerInformationByUsername(userService.getLoggedUser().getUsername());
        Set<Chat> chats = chatService.getByUsers(loggedUser);
        Set<ChatResponse> chatResponses = new HashSet<>();
        chats.forEach(chat -> {
            Set<UserBasicResponse> users = new HashSet<>();
            chat.getUsers().forEach(user -> users.add(UserBasicResponse.builder()
                    .username(user.getUsername())
                    .firstname(user.getFirstName())
                    .lastname(user.getLastName())
                    .build()));
            chatResponses.add(ChatResponse.builder().chatId(chat.getId()).name(generateName(users, loggedUser.getNickName())).users(users).build());
        });
        return ResponseEntity.accepted().body(chatResponses);

    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/getMessages/{id}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String id) {
        return ResponseEntity.accepted().body(
                chatService.getById(id).getMessages().stream().map(msg-> Message.builder()
                        .possibleToxicity(msg.isPossibleToxicity())
                        .postTime(msg.getPostTime())
                        .message(msg.getMessage())
                        .sender(msg.getSender())
                        .build()).collect(Collectors.toList())
        );

    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/sendMessage/{id}")
    public ResponseEntity<EntityResponse> sendMessage(@RequestBody MessageRequest messageRequest, @PathVariable String id) {
        if (!messageIntegrator.isMessageToxic(messageRequest)) {
            GamerInformation loggedGamer = userService.getGamerInformationByUsername(userService.getLoggedUser().getUsername());
            chatService.sendMessage(id, Message.builder().sender(Chat.SimpleUser.builder()
                    .username(loggedGamer.getNickName())
                    .firstName(loggedGamer.getFirstName())
                    .lastName(loggedGamer.getLastName())
                    .build()
            ).message(messageRequest.getMsgText()).possibleToxicity(false).postTime(new Date()).build());
            return ResponseEntity.accepted().body(EntityResponse.builder().message("Message was send").build());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(EntityResponse.builder().message("Message possibly contains questionable content").build());
        }
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("/sendToxicMessage/{id}")
    public ResponseEntity<EntityResponse> sendToxicMessage(@RequestBody MessageRequest messageRequest, @PathVariable String id) {
        GamerInformation loggedGamer = userService.getGamerInformationByUsername(userService.getLoggedUser().getUsername());
        chatService.sendMessage(id, Message.builder().sender(Chat.SimpleUser.builder()
                .username(loggedGamer.getNickName())
                .firstName(loggedGamer.getFirstName())
                .lastName(loggedGamer.getLastName())
                .build()
        ).message(messageRequest.getMsgText()).possibleToxicity(true).postTime(new Date()).build());
        return ResponseEntity.accepted().body(EntityResponse.builder().message("Message was send").build());

    }
}
