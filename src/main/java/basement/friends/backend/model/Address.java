package basement.friends.backend.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Address {
    private String country;

    private String city;

    private LocalDate localTime;
}
