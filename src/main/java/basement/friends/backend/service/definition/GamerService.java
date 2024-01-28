package basement.friends.backend.service.definition;

import basement.friends.backend.model.Gamer;
import basement.friends.backend.model.UserGameRecord;

import java.util.Set;

public interface GamerService {

    Gamer getGamerByNickname(String nickname);

    Gamer getExtendedUserInfo(String id);

    Set<Gamer> getExtendedUserInfos();

    void addRank(String username, String rank);

    void addFriend(String loggedUsername, String friendUsername);

    void addGame(String nickname, UserGameRecord userGameRecord);

    Set<Gamer> getFriends(String username);

    void updateUser(Gamer gamer);
}
