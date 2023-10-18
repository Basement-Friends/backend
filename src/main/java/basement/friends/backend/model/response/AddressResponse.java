package basement.friends.backend.model.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddressResponse {
    private String country;

    private String city;

    private LocalDate localTime;
}
