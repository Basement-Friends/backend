package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@Document
public class Message {
    @Id
    private String id;

    private User sender;

    private User receiver;

    private String message;

    private Date postTime;
}
