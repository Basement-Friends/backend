package basement.friends.backend.service.implementation;

import basement.friends.backend.exception.GamerInfoNotFoundException;
import basement.friends.backend.exception.RankNotFoundException;
import basement.friends.backend.exception.UserIdNotFoundException;
import basement.friends.backend.exception.UsernameNotFoundException;
import basement.friends.backend.model.Chat;
import basement.friends.backend.model.Gamer;
import basement.friends.backend.model.Rank;
import basement.friends.backend.model.UserGameRecord;
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
    public Gamer getGamerByNickname(String nickname) {
        return gamerRepository.getGamerByNickName(nickname).orElseThrow(GamerInfoNotFoundException::new);
    }

    @Override
    public Gamer getExtendedUserInfo(String id) {
        return gamerRepository.findById(id).orElseThrow(GamerInfoNotFoundException::new);
    }

    @Override
    public Set<Gamer> getExtendedUserInfos() {
        return new HashSet<>(gamerRepository.findAll());
    }

    @Override
    public void addRank(String username, String rankName) {
        String userId = userRepository.getUserByUsername(username).orElseThrow(UsernameNotFoundException::new).getId();

        Gamer gamer = gamerRepository.findById(userId).orElseThrow(UserIdNotFoundException::new);

        Rank rank = rankRepository.getByName(rankName).orElseThrow(RankNotFoundException::new);

        gamer.addRank(rank);
        gamerRepository.save(gamer);
    }

    @Override
    public void addFriend(String loggedUsername, String friendUsername) {
        Gamer loggedGamer = getGamerByNickname(loggedUsername);
        Gamer friend = getGamerByNickname(friendUsername);
        loggedGamer.addFriend(Chat.SimpleUser.builder()
                .firstName(friend.getFirstName())
                .lastName(friend.getLastName())
                .username(friendUsername)
                .build()
        );
        gamerRepository.save(loggedGamer);
        friend.addFriend(Chat.SimpleUser.builder()
                .firstName(loggedGamer.getFirstName())
                .lastName(loggedGamer.getLastName())
                .username(loggedUsername)
                .build()
        );
        gamerRepository.save(friend);
    }

    @Override
    public void addGame(String nickname, UserGameRecord userGameRecord) {
       Gamer gamer = getGamerByNickname(nickname);
       gamer.addGameRecord(userGameRecord);
       gamerRepository.save(gamer);

    }

    @Override
    public Set<Gamer> getFriends(String username) {
        Set<Gamer> friends = new HashSet<>();
        Gamer gamer = getGamerByNickname(username);
        if (gamer.getFriends() == null) {
           return new HashSet<>();
        }
        gamer.getFriends().forEach(friend-> friends.add(getGamerByNickname(friend.getUsername())));
        return friends;
    }

    @Override
    public void updateUser(Gamer gamer) {
        gamerRepository.save(gamer);
    }
}
