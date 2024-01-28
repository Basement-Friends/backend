package basement.friends.backend.model.DTO.request;

import lombok.Data;

import java.util.Date;

@Data
public class GamerInformationRequest {

    String game;

    Date gameStartDate;

    String additionalInformation;

}
