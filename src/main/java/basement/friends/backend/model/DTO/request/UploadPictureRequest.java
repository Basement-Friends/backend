package basement.friends.backend.model.DTO.request;

import basement.friends.backend.exception.NotValidPictureException;
import basement.friends.backend.model.Picture;
import basement.friends.backend.model.User;
import jakarta.activation.MimetypesFileTypeMap;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Data
public class UploadPictureRequest {
    private boolean checkIfValidPicture(MultipartFile file) {
        String filepath = file.getOriginalFilename();
        assert filepath != null;
        File f = new File(filepath);
        String mimetype = new MimetypesFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];
        return type.equals("image");
    }

    public Picture profilePictureRequest(MultipartFile file, User owner) throws IOException {
        if (!checkIfValidPicture(file))
            throw new NotValidPictureException();
        else {
            return Picture.builder()
                    .user(owner)
                    .id(owner.getId())
                    .content(file.getBytes())
                    .name(file.getName())
                    .size(file.getSize())
                    .build();
        }

    }

}
