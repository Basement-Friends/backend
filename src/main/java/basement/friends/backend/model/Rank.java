package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Rank {
    @Id
    private String id;

    private String rank;

    @Builder
    public Rank(String rank) {
        this.rank = rank;
    }

}
