package basement.friends.backend.service.definition;

import basement.friends.backend.model.Chat;
import basement.friends.backend.model.Gamer;
import basement.friends.backend.model.Message;

import java.util.Set;

public interface ChatService {

    Chat getById(String id);

    Set<Chat> getByUsers(Gamer... gamers);

    Chat createChat(Chat chat);

    Chat addUser(Chat chat, Gamer gamer);

    Chat sendMessage(String id, Message message);

    void delete(String id);

    void addUsers(String id, Gamer... gamer);

    void deleteUsers(String id, Gamer... gamer);
}
