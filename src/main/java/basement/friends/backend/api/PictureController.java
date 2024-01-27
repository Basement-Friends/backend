package basement.friends.backend.api;

import basement.friends.backend.integration.ProfilePictureValidator;
import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.model.Picture;
import basement.friends.backend.model.User;
import basement.friends.backend.service.definition.GamerService;
import basement.friends.backend.service.definition.PictureFactory;
import basement.friends.backend.service.definition.PictureService;
import basement.friends.backend.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/picture")
public class PictureController {
    private final PictureService pictureService;
    private final PictureFactory pictureFactory;
    private final UserService userService;
    private final GamerService gamerService;
    private final ProfilePictureValidator profilePictureValidator;


    @PreAuthorize("permitAll()")
    @PostMapping("/upload")
    public ResponseEntity<EntityResponse> uploadMyProfilePicture(@RequestParam("file") MultipartFile file) throws IOException {
        User loggedUser = userService.getLoggedUser();
        Picture profilePicture = pictureFactory.createFromFile(file, loggedUser);
        pictureService.savePicture(profilePicture);
        String validationMsg = profilePictureValidator.getPictureValidationMsg(file);
        if (profilePictureValidator.isValid()) {
            gamerService.addRank(loggedUser.getUsername(), "validated");
            validationMsg = STR."\{validationMsg} Account was validated.";
        }
        return ResponseEntity.accepted()
                .body(EntityResponse.builder()
                        .message(STR."Picture was uploaded successfully. \{validationMsg}")
                        .build());
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = "/view", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ByteArrayResource> viewPicture() {
        User loggedUser = userService.getLoggedUser();
        Picture picture = pictureService.getPictureByUsername(loggedUser.getUsername());
        ByteArrayResource resource = new ByteArrayResource(picture.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + picture.getName() + "\"")
                .body(resource);
   }


    @PreAuthorize("permitAll()")
    @GetMapping(value = "/{username}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ByteArrayResource> getPicture(@PathVariable String username) {
        Picture picture = pictureService.getPictureByUsername(username);
        ByteArrayResource resource = new ByteArrayResource(picture.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + picture.getName() + "\"")
                .body(resource);
    }

}
