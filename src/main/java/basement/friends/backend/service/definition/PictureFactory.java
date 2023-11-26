package basement.friends.backend.service.definition;

import basement.friends.backend.model.Picture;
import basement.friends.backend.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface PictureFactory {
    Picture createFromFile(MultipartFile file, User owner);
}
