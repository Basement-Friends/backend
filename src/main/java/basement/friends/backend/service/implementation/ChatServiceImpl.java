package basement.friends.backend.service.implementation;

import basement.friends.backend.exception.ChatNotFoundException;
import basement.friends.backend.model.Chat;
import basement.friends.backend.model.Gamer;
import basement.friends.backend.model.Message;
import basement.friends.backend.repository.ChatRepository;
import basement.friends.backend.service.definition.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;

    @Override
    public Chat getById(String id) {
        Chat foundChat = chatRepository.findById(id)
                .orElseThrow(ChatNotFoundException::new);
        return foundChat;
    }

    @Override
    public Set<Chat> getByUsers(Gamer... gamers) {
        Set<Chat.SimpleUser> usersSet = Arrays.stream(gamers).collect(Collectors.toSet()).stream().map(gamer -> Chat.SimpleUser.builder()
                .username(gamer.getNickName())
                .firstName(gamer.getFirstName())
                .lastName(gamer.getLastName())
                .build()).collect(Collectors.toSet());
        return chatRepository.getChatsByUsersIsContaining(usersSet);
    }

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Chat addUser(Chat chat, Gamer gamer) {
        Chat.SimpleUser simpleUser = Chat.SimpleUser.builder()
                .username(gamer.getNickName())
                .firstName(gamer.getFirstName())
                .lastName(gamer.getLastName())
                .build();
        chat.addUsers(simpleUser);
        return chatRepository.save(chat);
    }

    @Override
    public Chat sendMessage(String chatId, Message message) {
        Chat chat = this.getById(chatId);
        chat.addMessage(message);
        return chatRepository.save(chat);
    }

    @Override
    public void delete(String id) {
        Chat chat = this.getById(id);
        chatRepository.delete(chat);
    }

    @Override
    public void addUsers(String id, Gamer... gamers) {
        Chat chat = this.getById(id);
        Arrays.stream(gamers).map(g ->
                Chat.SimpleUser.builder()
                        .username(g.getNickName())
                        .firstName(g.getFirstName())
                        .lastName(g.getLastName())
                        .build()).forEach(chat::addUsers);
        chatRepository.save(chat);
    }

    @Override
    public void deleteUsers(String id, Gamer... gamers) {
        Chat chat = this.getById(id);
        Arrays.stream(gamers).map(g ->
                Chat.SimpleUser.builder()
                        .username(g.getNickName())
                        .firstName(g.getFirstName())
                        .lastName(g.getLastName())
                        .build()).forEach(chat::deleteUserFromChat);
        chatRepository.save(chat);

    }

}
