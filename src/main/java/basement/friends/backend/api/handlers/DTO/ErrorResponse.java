package basement.friends.backend.api.handlers.DTO;

import basement.friends.backend.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;

    public ErrorResponse(BusinessException exception) {
        this.status = exception.getStatus();
        this.message = exception.getMessage();
    }
}
