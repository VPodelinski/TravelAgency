package by.vitali.domain.services.interfaces;

import by.vitali.infrastructure.model.Tour;

import java.util.Map;

public interface TourManagement extends GeneralManagement<Tour> {

    void makeDiscount(int idTour, int discount) throws Exception;

    Map<Integer, String> getMapToursByRequest(int tourType, int country, int transport, int hotelType, int foodComplex) throws Exception;

    String convertTourToString(int id) throws Exception;

    Map<Integer, String> getToursMapLimit(int startRecord, int sizePage) throws Exception;

    int getCountTours() throws Exception;

    void createTour(int fk_country, int fk_tour_type, int fk_transport, int fk_hotel_type, int fk_food_complex, int cost) throws Exception;

}
