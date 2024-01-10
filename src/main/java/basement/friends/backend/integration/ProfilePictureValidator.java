package basement.friends.backend.integration;

import basement.friends.backend.exception.FailedToConnectException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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


    private void setHeaders() {
        this.headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
    }


    public boolean isPictureValid(MultipartFile file) throws IOException {

        byte [] byteArray = file.getBytes();

        String data = "\"data\": "+byteArray.toString();

        try {
            this.setHeaders();
            ResponseEntity<String> response = restTemplate.postForEntity(
                    this.URI,
                    new HttpEntity<>(file.getBytes(), this.headers),
                    String.class);
            if (Objects.equals(response.getBody(), "0")) {
                System.out.println(response.getBody());
                return false;
            } else {
                System.out.println(response.getBody());
                return true;
            }
        } catch (Exception e) {
            throw new FailedToConnectException(e.getMessage());

        }
    }
}
