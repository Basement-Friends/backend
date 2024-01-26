package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Message {

    private Chat.SimpleUser sender;

    private String message;

    private Date postTime;

    boolean possibleToxicity;
}
