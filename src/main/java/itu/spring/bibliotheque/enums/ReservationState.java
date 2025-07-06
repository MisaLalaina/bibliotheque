package itu.spring.bibliotheque.enums;

public enum ReservationState {
    PENDING,
    VALIDATED,
    EXPIRED,
    REJECTED,
    CANCELED;
    public String getLabel() {
        return this.name().toLowerCase();
    }
}
