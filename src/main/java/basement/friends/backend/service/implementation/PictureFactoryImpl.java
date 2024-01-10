package basement.friends.backend.service.implementation;

import basement.friends.backend.exception.NotValidPictureException;
import basement.friends.backend.model.Picture;
import basement.friends.backend.model.User;
import basement.friends.backend.service.definition.PictureFactory;
import jakarta.activation.MimetypesFileTypeMap;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class PictureFactoryImpl implements PictureFactory {

    private boolean checkIfValidPicture(MultipartFile file) {
        String filepath = file.getOriginalFilename();
        assert filepath != null;
        File f = new File(filepath);
        String mimetype = new MimetypesFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];
        return type.equals("image");
    }
    @Override
    public Picture createFromFile(MultipartFile file, User owner) {
        if (!checkIfValidPicture(file))
            throw new NotValidPictureException();
        else {
            try {
                return Picture.builder()
                        .user(owner)
                        .id(owner.getId())
                        .content(file.getBytes())
                        .name(file.getName())
                        .size(file.getSize())
                        .build();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
