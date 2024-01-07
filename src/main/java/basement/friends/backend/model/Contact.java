package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Contact {
    String userId;
    String username;
    Date contactCreationDate;
}
