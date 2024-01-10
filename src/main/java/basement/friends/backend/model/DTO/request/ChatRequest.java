package basement.friends.backend.model.DTO.request;

import lombok.Data;

import java.util.Set;

@Data
public class ChatRequest {
    Set<String> usernames;


}
