package basement.friends.backend.service.implementation;

import basement.friends.backend.model.Rank;
import basement.friends.backend.repository.RankRepository;
import basement.friends.backend.service.definition.RankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RankServiceImpl implements RankService {
    private final RankRepository rankRepository;

    @Override
    public Set<Rank> getAllRanks() {
        return new HashSet<>(rankRepository.findAll());
    }

    @Override
    public Rank getRankByName(String name) {
        return rankRepository.getByName(name);
    }

    @Override
    public void saveRank(Rank rank) {
        rankRepository.save(rank);
    }

    @Override
    public void deleteRank(String name) {
        Rank rank = rankRepository.getByName(name);
        rankRepository.delete(rank);
    }
}
