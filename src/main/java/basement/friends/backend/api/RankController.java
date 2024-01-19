package basement.friends.backend.api;

import basement.friends.backend.model.DTO.request.RankRequest;
import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.model.DTO.response.RankResponse;
import basement.friends.backend.model.Rank;
import basement.friends.backend.service.definition.RankService;
import basement.friends.backend.service.definition.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/rank")
public class RankController {

    private final RankService rankService;

    private final UserService userService;

    @PreAuthorize("hasAuthority({'ROLE_ADMIN'})")
    @GetMapping("/all")
    public ResponseEntity<Set<RankResponse>> getAllRanks() {
        return ResponseEntity.ok().body(rankService.getAllRanks().stream().map(rank -> RankResponse.builder().rank(rank.getName()).build()).collect(Collectors.toSet()));
    }

    @PreAuthorize("hasAuthority({'ROLE_ADMIN'})")
    @PostMapping("/create")
    public ResponseEntity<EntityResponse> createRank(@RequestBody RankRequest request) {
        rankService.saveRank(Rank.builder().name(request.getName()).build());
        return ResponseEntity.ok().body(EntityResponse.builder().message("Rank was successfully created").build());
    }

}
