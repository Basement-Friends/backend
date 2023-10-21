package basement.friends.backend.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadPictureRequest {
    MultipartFile file;
    String username;
}
