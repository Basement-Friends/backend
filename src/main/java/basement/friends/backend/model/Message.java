package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Builder
public class Message {
    @Id
    private String id;

    private Chat.SimpleUser sender;

    private String message;

    private Date postTime;

    boolean possibleToxicity;
}
