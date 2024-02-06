package basement.friends.backend.model.DTO.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasicUserRequest {
    String firstname;
    String lastname;
    String email;
    String username;

}
