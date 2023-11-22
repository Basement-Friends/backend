package basement.friends.backend.model.enums;

public enum Role {
    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN;

    public String getName() {
        return this.name();
    }
}
