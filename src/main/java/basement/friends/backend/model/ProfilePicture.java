package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class ProfilePicture {
    private String username;
    private long size;
    private byte [] content;

}
