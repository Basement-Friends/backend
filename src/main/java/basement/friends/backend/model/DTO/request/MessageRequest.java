package basement.friends.backend.model.DTO.request;

import lombok.Data;

@Data
public class MessageRequest {
    String chatId;
    String msgText;
}
