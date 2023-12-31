package basement.friends.backend.model.DTO.response;

import basement.friends.backend.model.UserGameRecord;
import basement.friends.backend.model.enums.Gender;

import java.time.LocalDate;
import java.util.Set;



public class UserInfoResponse {
    private String firstName;
    private String lastName;

    private String nickName;

    private Gender gender;

    private Set<UserGameRecord> gameRecords;

    private Set<String> ranks;

    private String country;

    private String city;

    private LocalDate localTime;
}
