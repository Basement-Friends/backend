package basement.friends.backend.service.definition;

import basement.friends.backend.model.Chat;
import basement.friends.backend.model.Gamer;

import java.util.Set;

public interface ChatFactory {
    Chat createFromRequest(Set<Gamer> users);
}
