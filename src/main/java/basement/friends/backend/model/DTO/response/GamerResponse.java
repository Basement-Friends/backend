package basement.friends.backend.model.DTO.response;

import basement.friends.backend.model.Rank;
import basement.friends.backend.model.UserGameRecord;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class GamerResponse {

    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    private String nickname;

    private List<UserGameRecord> gameRecords;

    private Set<Rank> ranks;

    private String country;

    private String city;

    private LocalDate localTime;

    private String pictureUrl;

}
