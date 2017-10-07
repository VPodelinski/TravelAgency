package by.vitali.infrastructure.model;

/**
 * This enum lists the countries where you can go on vacation.
 */

public enum Country {

    /**
     * Travel to India.
     */
    INDIA("India"),

    /**
     * Travel to France.
     */
    FRANCE("France"),

    /**
     * Travel to Spain.
     */
    SPAIN("Spain"),

    /**
     * Travel to England.
     */
    ENGLAND("England"),

    /**
     * Travel to Netherlands.
     */
    NETHERLANDS("Netherlands"),

    /**
     * Travel to Thailand.
     */
    THAILAND("Thailand"),

    /**
     * Travel to Austria.
     */
    AUSTRIA("Austria"),

    /**
     * Travel to Italy.
     */
    ITALY("Italy"),

    /**
     * Travel to China.
     */
    CHINA("China"),

    /**
     * Travel to Czech Republic.
     */
    CZECH_REPUBLIC("Czech Republic"),

    /**
     * Travel to Turkey.
     */
    TURKEY("Turkey");

    private String name;

    Country(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
