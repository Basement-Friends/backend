package basement.friends.backend.service.definition;

import basement.friends.backend.model.GamerInformation;

import java.util.Set;

public interface GamerService {

    GamerInformation getExtendedUserInfo(String id);

    Set<GamerInformation> getExtendedUserInfos();

    void addRank(String username, String rank);
}
