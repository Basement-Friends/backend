package basement.friends.backend.model.enums;

public enum Gender {
    MALE,
    FEMALE,
    NON_BINARY,
    OTHER;

    public String getName() {
        return this.name();
    }
}