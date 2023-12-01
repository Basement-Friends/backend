package basement.friends.backend.service.implementation;

import basement.friends.backend.model.Chat;
import basement.friends.backend.model.User;
import basement.friends.backend.service.definition.ChatFactory;

import java.util.Set;

public class ChatFactoryImpl implements ChatFactory {
    @Override
    public Chat createFromRequest(Set<User> users) {
        return Chat.builder()
                .users(users)
                .build();
    }
}
