package basement.friends.backend.service.definition;

import basement.friends.backend.model.Game;

import java.util.Set;

public interface GameService {
    Set<Game> getAllGames();

    void addGame(Game game);

    void importGames(Set<Game> games);

}
