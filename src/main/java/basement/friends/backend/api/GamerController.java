package basement.friends.backend.api;

import basement.friends.backend.model.DTO.request.GamerInformationRequest;
import basement.friends.backend.model.DTO.request.RankRequest;
import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.model.DTO.response.GamerResponse;
import basement.friends.backend.model.Game;
import basement.friends.backend.model.Gamer;
import basement.friends.backend.model.User;
import basement.friends.backend.model.UserGameRecord;
import basement.friends.backend.service.definition.GameService;
import basement.friends.backend.service.definition.GamerService;
import basement.friends.backend.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/gamer")
public class GamerController {

    private final UserService userService;
    private final GamerService gamerService;
    private final GameService gameService;

    @PreAuthorize("permitAll()")
    @GetMapping("/{username}")
    ResponseEntity<GamerResponse> getGamerByUsername(@PathVariable String username) {
        Gamer gamer = gamerService.getGamerByNickname(username);
        return ResponseEntity.accepted().body(GamerResponse.builder().firstName(gamer.getFirstName()).lastName(gamer.getLastName()).nickname(gamer.getNickName()).gameRecords(gamer.getGameRecords()).build());

    }

    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    ResponseEntity<Set<GamerResponse>> getAllGamers() {
        return ResponseEntity.accepted().body(gamerService.getExtendedUserInfos().stream().map(gamer-> GamerResponse.builder().firstName(gamer.getFirstName()).lastName(gamer.getLastName()).nickname(gamer.getNickName()).gameRecords(gamer.getGameRecords()).build()).collect(Collectors.toSet())
        );
    }

    @PreAuthorize("hasAuthority({'ROLE_USER'})")
    @GetMapping("/friends")
    ResponseEntity<Set<GamerResponse>> getMyFriends() {
        User loggedUser = userService.getLoggedUser();
        return ResponseEntity.accepted().body(gamerService.getFriends(loggedUser.getUsername()).stream().map(gamer-> GamerResponse.builder().firstName(gamer.getFirstName()).lastName(gamer.getLastName()).nickname(gamer.getNickName()).gameRecords(gamer.getGameRecords()).build()).collect(Collectors.toSet())
        );
    }

    @PreAuthorize("hasAuthority({'ROLE_USER'})")
    @GetMapping()
    ResponseEntity<GamerResponse> getLoggedGamer() {
        User loggedUser = userService.getLoggedUser();
        Gamer gamer = gamerService.getExtendedUserInfo(loggedUser.getId());
        return ResponseEntity.accepted().body(GamerResponse.builder().firstName(gamer.getFirstName()).lastName(gamer.getLastName()).nickname(gamer.getNickName()).gameRecords(gamer.getGameRecords()).ranks(gamer.getRanks()).build());

    }

    @PreAuthorize("hasAuthority({'ROLE_ADMIN'})")
    @PutMapping("/addRank/{username}")
    public ResponseEntity<EntityResponse> addRankToUser(@RequestBody RankRequest request, @PathVariable String username) {
        gamerService.addRank(username, request.getName());
        return ResponseEntity.accepted().body(EntityResponse.builder().message("Rank was added to user").build());
    }
    @PreAuthorize("hasAuthority({'ROLE_USER'})")
    @PutMapping("/updateProfile")
    public ResponseEntity<EntityResponse> updateProfile(@RequestBody GamerInformationRequest request) {
        User logggedUser = userService.getLoggedUser();
        Gamer gamer = gamerService.getExtendedUserInfo(logggedUser.getId());
        gamer.addGameRecord(UserGameRecord.builder()
                .game(Game.builder()
                        .name(request.getGame())
                        .build())
                .gameStartDate(request.getGameStartDate() != null ? request.getGameStartDate() : new Date())
                .additionalInformation(request.getAdditionalInformation())
                .build()
        );
        gamerService.updateUser(gamer);
        gameService.addGame(Game.builder().name(request.getGame()).build());
        return ResponseEntity.ok().body(EntityResponse.builder().message("Profile was successfully updated").build());
    }
}
