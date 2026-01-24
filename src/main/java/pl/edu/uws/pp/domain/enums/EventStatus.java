package pl.edu.uws.pp.domain.enums;

public enum EventStatus {
    PLANNED,
    CONFIRMED,
    CANCELLED,
    ENDED;

    public boolean canChangeTo(EventStatus newStatus) {
        return switch (this){
            case PLANNED -> newStatus == CONFIRMED || newStatus == CANCELLED;
            case CONFIRMED -> newStatus == CANCELLED || newStatus == ENDED;
            case CANCELLED, ENDED -> false;
        };
    }
}
