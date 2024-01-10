package basement.friends.backend.model.DTO.request;

import lombok.Data;

@Data
public class BasicUserRequest {
    String firstname;
    String lastname;
    String email;
    String username;

}
