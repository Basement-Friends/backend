package basement.friends.backend.model.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Optional;

@Data
@Builder
public class RequestResponse {
    String id;
    String type;
    Optional<String> to;
    String from;
    Date creationDate;
    Date updateDate;
}
