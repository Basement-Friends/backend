package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Picture {
    @Id
    private String id;
    private String name;
    private long size;
    private byte [] content;

    private User user;


}
