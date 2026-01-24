package pl.edu.uws.pp.domain.enums;

public enum FailureStatus {
    NEW,
    IN_PROGRESS,
    RESOLVED;

    public boolean cantChangeTo(FailureStatus newStatus) {
        return !switch (this) {
            case NEW -> newStatus == IN_PROGRESS;
            case IN_PROGRESS -> newStatus == RESOLVED;
            case RESOLVED -> false;
        };
    }
}
