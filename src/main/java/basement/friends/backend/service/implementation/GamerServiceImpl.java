package basement.friends.backend.service.implementation;

import basement.friends.backend.exception.GamerInfoNotFoundException;
import basement.friends.backend.exception.RankNotFoundException;
import basement.friends.backend.exception.UserIdNotFoundException;
import basement.friends.backend.exception.UsernameNotFoundException;
import basement.friends.backend.model.GamerInformation;
import basement.friends.backend.model.Rank;
import basement.friends.backend.repository.GamerRepository;
import basement.friends.backend.repository.RankRepository;
import basement.friends.backend.repository.UserRepository;
import basement.friends.backend.service.definition.GamerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class GamerServiceImpl implements GamerService {
    private final GamerRepository gamerRepository;
    private final UserRepository userRepository;

    private final RankRepository rankRepository;
    @Override
    public GamerInformation getExtendedUserInfo(String id) {
        return gamerRepository.findById(id).orElseThrow(GamerInfoNotFoundException::new);
    }

    @Override
    public Set<GamerInformation> getExtendedUserInfos() {
        return new HashSet<>(gamerRepository.findAll());
    }

    @Override
    public void addRank(String username, String rankName) {
        String userId = userRepository.getUserByUsername(username).orElseThrow(UsernameNotFoundException::new).getId();

        GamerInformation gamer = gamerRepository.findById(userId).orElseThrow(UserIdNotFoundException::new);

        Rank rank = rankRepository.getByName(rankName).orElseThrow(RankNotFoundException::new);

        gamer.addRank(rank);
        gamerRepository.save(gamer);
    }
}
