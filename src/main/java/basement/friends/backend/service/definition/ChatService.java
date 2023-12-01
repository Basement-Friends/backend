package basement.friends.backend.service.definition;

import basement.friends.backend.model.Chat;
import basement.friends.backend.model.Message;
import basement.friends.backend.model.User;

import java.util.Set;

public interface ChatService {

    Chat getById(String id);

    Set<Chat> getByUsers(User... users);

    Chat createChat(Chat chat);

    Chat addUser(Chat chat, User user);

    Chat sendMessage(String id, Message message);

    void delete(String id);

    void addUsers(String id, User... users);

    void deleteUsers(String id, User... user);
}
