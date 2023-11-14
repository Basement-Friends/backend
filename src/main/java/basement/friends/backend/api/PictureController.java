package basement.friends.backend.api;

import basement.friends.backend.service.definition.PictureService;
import basement.friends.backend.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/picture")
public class PictureController {
    private final PictureService pictureService;
    private final UserService userService;

//    @PostMapping("/upload")
//    public ResponseEntity<Picture> uploadMyProfilePicture(@RequestParam("file") MultipartFile file) throws IOException {
//        User loggedUser = userService.getLoggedUser();
//        Picture profilePicture = UploadPictureRequest.builder().file(file).username(username)()file, loggedUser);
//        return ResponseEntity.accepted().body(profilePicture);
//    }
//
//    @PostMapping("/upload/{username}")
//    public ResponseEntity<Picture> uploadUsersProfilePicture(@PathVariable String username, @RequestParam("file") MultipartFile file) throws IOException {
//        User user = userService.getByUsername(username);
//        Picture profilePicture = new AddProfilePictureRequest().profilePictureRequest(file, user);
//        return ResponseEntity.accepted().body(ProfilePictureResponse.builder()
//                .profilePicture(profilePictureService.saveProfilePicture(profilePicture))
//                .build());
//    }
}
