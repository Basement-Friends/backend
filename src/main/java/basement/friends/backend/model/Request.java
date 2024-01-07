package basement.friends.backend.model;

import basement.friends.backend.model.enums.RequestStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@Builder
public class Request {

    String userId;

    User initiator;

    RequestStatus status;

    Date creationDate;

    Date updateDate;


}
