package basement.friends.backend.integration;

import basement.friends.backend.exception.FailedToConnectException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ProfilePictureValidator {
    @Value("${model.picture-validator.url}")
    String URI;
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Getter
    boolean valid = false;


    private void setHeaders() {
        this.headers.setContentType(MediaType.MULTIPART_FORM_DATA);
    }


    public String getPictureValidationMsg(MultipartFile file) throws IOException {
        try {
            this.setHeaders();
            MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
            parts.add("file", new HttpEntity<>(file.getResource(), headers));
            ResponseEntity<String> response = restTemplate.postForEntity(
                    this.URI,
                    parts,
                    String.class);
            return switch (Objects.requireNonNull(response.getBody())) {
                case "0" -> "No human detected";
                case "1" -> "Presence of more than one person detected.";
                case "3" -> "Person is under 16 years old.";
                case "4" -> {
                    this.valid = true;
                    yield "Person is 16 years old or older.";
                }
                default -> "Other error";
            };
        } catch (Exception e) {
            throw new FailedToConnectException(e.getMessage());

        }
    }
}
