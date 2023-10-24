package basement.friends.backend.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedRegisterRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String gender;
}
