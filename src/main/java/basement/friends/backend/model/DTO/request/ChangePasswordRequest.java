package basement.friends.backend.model.DTO.request;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    String oldPassword;
    String newPassword;
    String repeatPassword;
}
