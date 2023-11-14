package basement.friends.backend.service.definition;

import basement.friends.backend.model.Picture;

public interface PictureService {
    Picture savePicture(Picture picture);

    Picture getPicture(String id);

    Picture getPictureByUsername(String username);
}
