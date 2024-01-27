package basement.friends.backend.model;

import basement.friends.backend.model.enums.Gender;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@Document
public class Gamer {
    @Id
    private String id;
    private String firstName;
    private String lastName;

    @Indexed(unique = true)
    private String nickName;

    private Gender gender;

    private List<UserGameRecord> gameRecords;

    private Set<Rank> ranks;

    private Set<Chat.SimpleUser> friends;

    private Address address;


    public void addRank(Rank rank) {
        if (this.ranks == null) {
            this.ranks = new HashSet<>();
        }
        this.ranks.add(rank);
    }

    public void addGameRecord(UserGameRecord gameRecord) {
        if (this.gameRecords == null) {
            this.gameRecords = new ArrayList<>();
        }
        this.gameRecords.add(gameRecord);
    }

    public void addFriend(Chat.SimpleUser simpleUser) {
        if (this.friends == null) {
            this.friends = new HashSet<>();
        }
        this.friends.add(simpleUser);
    }


}
