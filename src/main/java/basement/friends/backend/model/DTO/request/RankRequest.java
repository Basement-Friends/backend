package basement.friends.backend.model.DTO.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RankRequest {

    @NotBlank
    String name;
}
