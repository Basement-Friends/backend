package basement.friends.backend.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String nickname;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String city;
    private String country;
}
