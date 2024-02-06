package basement.friends.backend.model;

import basement.friends.backend.model.DTO.request.BasicUserRequest;
import basement.friends.backend.model.enums.RequestStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@Builder
public class Request {

    @Id
    String id;

    String userId;

    BasicUserRequest initiator;

    RequestStatus status;

    Date creationDate;

    Date updateDate;


}
