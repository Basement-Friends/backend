package basement.friends.backend.model.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ChatResponse {

    String chatId;

    String name;

    Set<UserBasicResponse> users;

    public static String generateName(Set<UserBasicResponse> users, String username) {
        final String[] name = {null};
        users.forEach(user -> {
            if (!user.username.equals(username)) {
                if (name[0] != null) {
                    name[0] =  STR."\{ name[0] }, \{user.firstname}";
                } else {
                    name[0] = STR."\{user.firstname}";
                }

            }
        });
        return name[0];

    }

}

