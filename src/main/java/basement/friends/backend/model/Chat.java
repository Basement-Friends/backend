package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;





@Data
@Document
@Builder
public class Chat {
    private Set<SimpleUser> users;
    @Id
    private String id;
    private List<Message> messages;

    public void addUsers(SimpleUser user) {
        users.add(user);
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void deleteUserFromChat(SimpleUser user) {
        users.remove(user);
    }

    @Builder
    @Getter
    public static final class SimpleUser {
        private String username;
        private String firstName;
        private String lastName;
    }

}
