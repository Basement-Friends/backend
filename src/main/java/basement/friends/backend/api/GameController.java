package basement.friends.backend.api;

import basement.friends.backend.model.DTO.response.EntityResponse;
import basement.friends.backend.model.Game;
import basement.friends.backend.service.definition.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("api/game")
public class GameController {
    private final GameService gameService;

    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    ResponseEntity<Set<String>> getAllGamers() {
        return ResponseEntity.accepted().body(gameService.getAllGames().stream().map(Game::getName).collect(Collectors.toSet())
        );
    }

    @PreAuthorize("hasAuthority({'ROLE_ADMIN'})")
    @PostMapping("/import")
    ResponseEntity<EntityResponse> importGames(@RequestBody Set<String> gameNames) {
        Set<Game> games = new HashSet<>();
        gameNames.forEach(game -> games.add(Game.builder().name(game).build()));
        gameService.importGames(games);
        return ResponseEntity.accepted().body(EntityResponse.builder().message("Games were successfully imported").build());

    }
}
