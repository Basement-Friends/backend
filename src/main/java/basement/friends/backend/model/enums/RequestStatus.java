package basement.friends.backend.model.enums;

public enum RequestStatus {
    PENDING,
    ACCEPTED,
    DECLINED;
    public String getName() {
        return this.name();
    }
}