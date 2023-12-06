package basement.friends.backend.model.DTO.response;

import basement.friends.backend.model.Message;
import basement.friends.backend.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class ChatResponse {

    String name;

    Set<User> users;

    List<Message> messages;

}
