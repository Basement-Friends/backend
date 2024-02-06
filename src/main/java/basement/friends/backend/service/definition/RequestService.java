package basement.friends.backend.service.definition;

import basement.friends.backend.model.Request;
import basement.friends.backend.model.User;

import java.util.Set;

public interface RequestService {
    Set<Request> getAllUserRequests(User user);

    void saveRequest(Request request);

    void updateRequest(String id, Request request);

    void deleteRequest(String id);

}
