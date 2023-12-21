package basement.friends.backend.api;

import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.model.Picture;
import basement.friends.backend.model.User;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("api/picture")
public class PictureController {
    private final PictureService pictureService;
    private final PictureFactory pictureFactory;
    private final UserService userService;

    @PreAuthorize("hasAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
    @PostMapping("/upload")
    public ResponseEntity<EntityResponse> uploadMyProfilePicture(@RequestParam("file") MultipartFile file) {
        User user = userService.getLoggedUser();
        Picture profilePicture = pictureFactory.createFromFile(file, user);
        pictureService.savePicture(profilePicture);
        return ResponseEntity.accepted()
                .body(EntityResponse.builder()
                        .message("Picture was uploaded successfully")
                        .build());
    }

    @PreAuthorize("hasAuthority({'ROLE_USER', 'ROLE_ADMIN'})")
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
    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<ByteArrayResource> getPicture(@PathVariable String id) {
        Picture picture = pictureService.getPicture(id);
        ByteArrayResource resource = new ByteArrayResource(picture.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + picture.getName() + "\"")
                .body(resource);
    }

}
