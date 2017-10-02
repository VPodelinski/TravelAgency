package by.vitali.infrastructure.model;

/**
 * This enum lists the countries where you can go on vacation.
 */

public enum Country {

    INDIA("India"),
    FRANCE("France"),
    SPAIN("Spain"),
    ENGLAND("England"),
    NETHERLANDS("Netherlands"),
    THAILAND("Thailand"),
    AUSTRIA("Austria"),
    ITALY("Italy"),
    CHINA("China"),
    CZECH_REPUBLIC("Czech Republic"),
    TURKEY("Turkey");

    private String name;

    Country(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
