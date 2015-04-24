package com.github.rakawestu.explorejogja.domain.repository.mock;

import com.github.rakawestu.explorejogja.domain.model.Category;
import com.github.rakawestu.explorejogja.domain.model.Place;
import com.github.rakawestu.explorejogja.domain.model.SubCategory;
import com.github.rakawestu.explorejogja.domain.repository.ExploreJogjaRepository;
import com.github.rakawestu.explorejogja.domain.repository.exception.GetPlaceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock repository for testing
 *
 * @author rakawm
 */
public class ExploreJogjaMockRepository implements ExploreJogjaRepository{
    @Override
    public List<Place> getPlaceCollection() throws GetPlaceException {
        return generateMockPlaces();
    }

    @Override
    public List<Category> getCategoryCollection() throws GetPlaceException {
        return null;
    }

    @Override
    public List<SubCategory> getSubCategoryCollection(int tipe) {
        return null;
    }

    private List<Place> generateMockPlaces() {
        List<Place> places = new ArrayList<>();
        places.add(createPlace(
                "Rumah Makan Sambingan", "Rumah makan paling enak di Jawa Tengah.", "Jl Raya Gowok",
                "25000-100000", "09.00-21.00", "http://sambingan.co.id", "Kamar mandi dalam, tempat tidur di luar",
                "Enak tapi mahal", "file:///C:/Users/raka/Pictures/chess_board2.png"
        ));

        places.add(createPlace(
                "Candi Peteng", "Wisata paling ajaib di Jawa Barat.", "Jl Petengan",
                "50000-100000", "07.00-17.00", "http://candipeteng.co.id", "Parkir luas, teduh",
                "Bagus sih.", "file:///C:/Users/raka/Pictures/chess_board2.png"
        ));
        return places;
    }

    private Place createPlace(String judul, String deskripsi, String id_alamat,
                              String price_range, String opening_hours, String cp_web, String facility,
                              String review, String photos){
        Place place = new Place(String.valueOf(getRandomNumber()), judul, deskripsi, id_alamat,
                price_range, opening_hours, cp_web, facility, review, photos);
        return place;
    }

    private int getRandomNumber() {
        return 1 + (int) (Math.random() * 999);
    }


}
