package itu.spring.bibliotheque.enums;

public enum ReservationState {
    PENDING,
    VALIDATED,
    REJECTED,
    CANCELED;
    public String getLabel() {
        return this.name().toLowerCase();
    }
}
