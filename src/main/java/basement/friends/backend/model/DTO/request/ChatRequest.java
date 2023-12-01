package basement.friends.backend.model.DTO.request;

import lombok.Data;

import java.util.List;

@Data
public class ChatRequest {
    List<String> usernames;


}
