package basement.friends.backend.service.implementation;

import basement.friends.backend.model.Chat;
import basement.friends.backend.model.GamerInformation;
import basement.friends.backend.service.definition.ChatFactory;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatFactoryImpl implements ChatFactory {

    @Override
    public Chat createFromRequest(Set<GamerInformation> users) {
        return Chat.builder()
                .users(users.stream().map(user -> Chat.SimpleUser.builder()
                        .username(user.getNickName())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .build()).collect(Collectors.toSet()))
                .build();
    }
}
