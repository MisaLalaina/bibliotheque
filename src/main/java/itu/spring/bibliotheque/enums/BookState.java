package itu.spring.bibliotheque.enums;

public enum BookState {
    AVAILABLE,
    CHECKED_OUT,
    RESERVED;

    public String getLabel() {
        return this.name().toLowerCase();
    }
}
