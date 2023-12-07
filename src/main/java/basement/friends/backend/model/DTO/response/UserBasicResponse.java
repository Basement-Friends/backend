package basement.friends.backend.model.DTO.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBasicResponse {

    String username;

    String firstname;

    String lastname;
}
