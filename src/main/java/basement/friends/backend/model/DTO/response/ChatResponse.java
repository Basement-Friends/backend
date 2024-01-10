package basement.friends.backend.model.DTO.response;

import basement.friends.backend.model.Message;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Builder
public class ChatResponse {

    String chatId;

    String name;

    Set<UserBasicResponse> users;

    List<Message> messages;

}
