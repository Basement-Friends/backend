package basement.friends.backend.service.definition;

import basement.friends.backend.model.Chat;
import basement.friends.backend.model.GamerInformation;

import java.util.Set;

public interface ChatFactory {
    Chat createFromRequest(Set<GamerInformation> users);
}
