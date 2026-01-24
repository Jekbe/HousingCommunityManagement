package pl.edu.uws.pp.domain.enums;

public enum InvoiceStatus {
    NEW,
    PAID,
    EXPIRED,
    CANCELLED;

    public boolean canChangeTo(InvoiceStatus newStatus) {
        return switch (this){
            case NEW -> newStatus == PAID || newStatus == EXPIRED || newStatus == CANCELLED;
            case PAID, CANCELLED -> false;
            case EXPIRED -> newStatus == PAID || newStatus == CANCELLED;
        };
    }
}
