package basement.friends.backend.service.definition;

import basement.friends.backend.model.Chat;
import basement.friends.backend.model.User;

import java.util.List;

public interface ChatService {

    Chat getById(String id);
    List<Chat> getByUsers(User ... users);
    Chat createChat(Chat chat);
    Chat addUser(User user);

    Chat sendMessage(Chat chat, String message);
    void deleteChat();
}
