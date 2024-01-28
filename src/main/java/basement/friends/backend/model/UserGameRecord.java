package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
@Builder
public class UserGameRecord {
    Game game;

    Set<GameAchievement> achievements;

    Date gameStartDate;

    String additionalInformation;


}
