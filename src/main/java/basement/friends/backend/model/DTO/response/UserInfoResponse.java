package basement.friends.backend.model.DTO.response;

import basement.friends.backend.model.UserGameRecord;
import basement.friends.backend.model.enums.Gender;
import lombok.Data;

import java.util.Set;


@Data
public class UserInfoResponse {
    private String firstName;
    private String lastName;
    private String nickName;

    private Gender gender;

    private Set<UserGameRecord> gameRecords;

    private Set<RankResponse> ranks;

    private AddressResponse address;
}
