package itu.spring.bibliotheque.enums;

public enum ReservationState {
    PENDING("En attente"),
    VALIDATED("Validée"),
    REJECTED("Rejetée"),
    CANCELED("Annulée");

    private final String label;

    ReservationState(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
