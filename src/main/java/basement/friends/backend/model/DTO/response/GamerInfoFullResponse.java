package basement.friends.backend.model.DTO.response;

import basement.friends.backend.model.Rank;
import basement.friends.backend.model.UserGameRecord;
import basement.friends.backend.model.enums.Gender;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.Set;

@Data
public class GamerInfoFullResponse {
    private String firstName;
    private String lastName;

    @Indexed(unique = true)
    private String nickName;

    private Gender gender;

    private Set<UserGameRecord> gameRecords;

    private Set<Rank> ranks;

    private String country;

    private String city;

    private LocalDate localTime;

    private String pictureUrl;

}
