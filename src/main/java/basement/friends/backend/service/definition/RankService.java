package basement.friends.backend.service.definition;

import basement.friends.backend.model.Rank;

import java.util.Set;

public interface RankService {

    Set<Rank> getAllRanks();

    Rank getRankByName(String name);

    void saveRank(Rank rank);

    void deleteRank(String name);
}
