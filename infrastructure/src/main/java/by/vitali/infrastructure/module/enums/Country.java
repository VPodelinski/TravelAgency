package by.vitali.infrastructure.module.enums;

/**
 * This enum lists the countries where you can go on vacation.
 */

public enum Country {

    COUNTRY1("INDIA"),
    COUNTRY2("FRANCE"),
    COUNTRY3("SPAIN"),
    Country4("ENGLAND"),
    COUNTRY5("NETHERLANDS"),
    COUNTRY6("THAILAND"),
    COUNTRY7("AUSTRIA"),
    COUNTRY8("ITALY"),
    COUNTRY9("CHINA"),
    COUNTRY10("CZECH REPUBLIC"),
    COUNTRY11("TURKEY");

    private String name;

    Country(String name) {
        this.name = name;
    }
}
