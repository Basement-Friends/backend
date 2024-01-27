package basement.friends.backend.service.definition;

import basement.friends.backend.model.Gamer;

import java.util.Set;

public interface GamerService {

    Gamer getGamerByNickname(String nickname);

    Gamer getExtendedUserInfo(String id);

    Set<Gamer> getExtendedUserInfos();

    void addRank(String username, String rank);

    void addFriend(String loggedUsername, String friendUsername);

    Set<Gamer> getFriends(String username);
}
