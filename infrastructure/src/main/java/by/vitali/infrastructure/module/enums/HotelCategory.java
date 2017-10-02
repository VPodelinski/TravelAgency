package by.vitali.infrastructure.module.enums;

public enum HotelCategory {

    HOTELCATEGORY1("1 STAR"),
    HOTELCATEGORY2("2 STARS"),
    HOTELCATEGORY3("3 STARS"),
    HOTELCATEGORY4("4 STARS"),
    HOTELCATEGORY5("5 STARS"),
    HOTELCATEGORY6("6 STARS"),
    HOTELCATEGORY7("7 STARS");

    private String category;

    HotelCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
