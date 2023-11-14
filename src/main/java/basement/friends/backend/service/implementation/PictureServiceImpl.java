package basement.friends.backend.service.implementation;

import basement.friends.backend.exception.PictureNotFoundException;
import basement.friends.backend.model.Picture;
import basement.friends.backend.repository.PictureRepository;
import basement.friends.backend.service.definition.PictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;


    @Override
    public Picture savePicture(Picture picture) {
        return pictureRepository.save(picture);
    }

    @Override
    public Picture getPicture(String id) {
        return pictureRepository.findById(id)
                .orElseThrow(PictureNotFoundException::new);
    }

    @Override
    public Picture getPictureByUsername(String username) {
        return pictureRepository.getByUser_Username(username)
                .orElseThrow(PictureNotFoundException::new);
    }
}
