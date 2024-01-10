package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@Document
@Builder
public class Chat {
    @Id
    private String id;

    Set<User> users;

    List<Message> messages;

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void addUsers(User user) {
        users.add(user);
    }
    public void deleteUserFromChat(User user) {
        users.remove(user);
    }

}
