package basement.friends.backend.model.DTO.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RankRequest {

    @NotNull
    String name;
}
