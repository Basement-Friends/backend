package basement.friends.backend.model.DTO.response;

import basement.friends.backend.model.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserResponse {
    String id;

    String username;

    String firstname;

    String lastname;

    String email;

    Set<Role> roles;


}
