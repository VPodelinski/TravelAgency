package by.vitali.infrastructure.model;

/**
 * This listing lists the type of meals.
 */
public enum TypeOfMeals {
    /**
     * Type of meal is BB (ВB — breakfast, beverages (tea, coffee, water)).
     */
    BB,

    /**
     * Type of meal is HB (HB — breakfast and dinner, beverages (tea, coffee, water)
     * are for free on breakfast, but are to be paid on dinner).
     */
    HB,

    /**
     * Type of meal is FB (FB — breakfast, lunch, dinner; beverages
     * (tea, coffee, water) are for free on breakfast, but are to be paid on lunch and dinner).
     */
    FB,

    /**
     * Type of meal is AL ((ALL INC) — all included: full board (breakfast, lunch, dinner),
     * alcoholic and non-alcoholic drinks of local production).
     */
    AL,

    /**
     * Type of meal is UAL (UAI — ultra all included: full board (breakfast, lunch, dinner),
     * alcoholic and non-alcoholic drinks of local production and some imported alcoholic drinks,
     * as well as additional services in the hotel).
     */
    UAL,

    /**
     * Type of meal is OB (OB — only bed, without meals).
     */
    OB

}
