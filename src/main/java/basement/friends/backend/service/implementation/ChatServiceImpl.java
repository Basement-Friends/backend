package basement.friends.backend.service.implementation;

import basement.friends.backend.repository.ChatRepository;
import basement.friends.backend.service.definition.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    @Override
    public void createChat() {
    }

    @Override
    public void addUser() {

    }

    @Override
    public void sendMessage() {

    }

    @Override
    public void deleteChat() {

    }
}
