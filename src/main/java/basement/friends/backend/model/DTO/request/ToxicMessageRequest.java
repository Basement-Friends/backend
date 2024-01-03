package basement.friends.backend.model.DTO.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ToxicMessageRequest {
    String message;
}
