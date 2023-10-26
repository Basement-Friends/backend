package basement.friends.backend.model;

import basement.friends.backend.model.enums.Gender;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@Builder
@Document
public class GamerInformation {
    @Id
    private String id;
    private String firstName;
    private String lastName;

    @Indexed(unique = true)
    private String nickName;

    private Gender gender;

    private Set<UserGameRecord> gameRecords;

    private Set<Rank> ranks;

    private Address address;





}
