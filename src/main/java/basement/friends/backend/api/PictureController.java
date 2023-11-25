package basement.friends.backend.api;

import basement.friends.backend.model.DTO.request.UploadPictureRequest;
import basement.friends.backend.model.Picture;
import basement.friends.backend.model.User;
import basement.friends.backend.service.definition.PictureService;
import basement.friends.backend.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/picture")
public class PictureController {
    private final PictureService pictureService;
    private final UserService userService;

    @Secured({"ROLE_USER"})
    @PostMapping("/upload/{username}")
    public ResponseEntity<Picture> uploadMyProfilePicture(@RequestParam("file") MultipartFile file, @PathVariable String username) throws IOException {
        User user = userService.getByUsername(username);
        Picture profilePicture = new UploadPictureRequest().profilePictureRequest(file, user);
        return ResponseEntity.accepted().body(pictureService.savePicture(profilePicture));
    }

}
