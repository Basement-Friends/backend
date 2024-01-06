package basement.friends.backend.integration;

import basement.friends.backend.exception.FailedToConnectException;
import basement.friends.backend.model.DTO.request.MessageRequest;
import basement.friends.backend.model.DTO.request.ToxicMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ToxicMessageValidator {
    @Value("${model.toxic-msg-validator.url}")
    String URI;
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();


    private void setHeaders() {
        this.headers.setContentType(MediaType.APPLICATION_JSON);
    }


    public boolean isMessageToxic(MessageRequest messageRequest) {
        ToxicMessageRequest toxicMsgRequest = ToxicMessageRequest.builder()
                .message(messageRequest.getMsgText())
                .build();

        try {
            this.setHeaders();
            ResponseEntity<String> response = restTemplate.postForEntity(
                    this.URI,
                    new HttpEntity<>(toxicMsgRequest, this.headers),
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
