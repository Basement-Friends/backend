package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfilePicture {
    private String username;
    private long size;
    private byte [] content;

}
