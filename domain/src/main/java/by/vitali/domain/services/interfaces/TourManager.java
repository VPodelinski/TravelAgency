package by.vitali.domain.services.interfaces;

import by.vitali.infrastructure.model.Tour;

import java.util.List;
import java.util.Map;

public class TourManager implements TourManagement {

    @Override
    public void save(Tour type) throws Exception {

    }

    @Override
    public void update(Tour type) throws Exception {

    }

    @Override
    public Tour read(int id) throws Exception {
        return null;
    }

    @Override
    public List<Tour> getAll() throws Exception {
        return null;
    }

    @Override
    public void makeDiscount(int idTour, int discount) throws Exception {

    }

    @Override
    public Map<Integer, String> getMapToursByRequest(int tourType, int country, int transport, int hotelType, int foodComplex) throws Exception {
        return null;
    }

    @Override
    public String convertTourToString(int id) throws Exception {
        return null;
    }

    @Override
    public Map<Integer, String> getToursMapLimit(int startRecord, int sizePage) throws Exception {
        return null;
    }

    @Override
    public int getCountTours() throws Exception {
        return 0;
    }

    @Override
    public void createTour(int fk_country, int fk_tour_type, int fk_transport, int fk_hotel_type, int fk_food_complex, int cost) throws Exception {

    }
}
