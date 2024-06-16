package com.javarush.domain;

public enum Continent {
    ASIA(0),
    EUROPE(1),
    NORTH_AMERICA(2),
    AFRICA(3),
    OCEANIA(4),
    ANTARCTICA(5),
    SOUTH_AMERICA(6);

    private final int value;

    Continent(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Continent fromValue(int value) {
        for (Continent continent : Continent.values()) {
            if (continent.value == value) {
                return continent;
            }
        }
        throw new IllegalArgumentException("Invalid value for continent enum: " + value);
    }
}
