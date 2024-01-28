package basement.friends.backend.service.implementation;

import basement.friends.backend.model.Game;
import basement.friends.backend.repository.GameRepository;
import basement.friends.backend.service.definition.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    @Override
    public Set<Game> getAllGames() {
        return new HashSet<>(gameRepository.findAll()) ;
    }

    @Override
    public void addGame(Game game) {
        if (gameRepository.existsByName(game.getName())) {
            return;
        }
        gameRepository.save(game);
    }

    @Override
    public void importGames(Set<Game> games) {
        if (games == null) {
            return;
        }
        gameRepository.saveAll(games);
    }
}
