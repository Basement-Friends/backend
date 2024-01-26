package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.Stack;

@Data
@Document
@Builder
public class Chat {
    private Set<SimpleUser> users;
    @Id
    private String id;
    private Stack<Message> messages;

    public void addUsers(SimpleUser user) {
        users.add(user);
    }

    public void addMessage(Message message) {
        if(this.messages == null) {
            this.messages = new Stack<>();
        }
        this.messages.push(message);
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
