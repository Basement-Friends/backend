package basement.friends.backend.model.enums;

public enum RequestStatus {
    PENDING,
    ACCEPTED,
    REJECTED;
    public String getName() {
        return this.name();
    }
}