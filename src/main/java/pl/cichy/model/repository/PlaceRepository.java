package pl.cichy.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import pl.cichy.model.Place;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface PlaceRepository {

    List<Place> findAll();

    Page<Place> findAll(Pageable page);

    Optional<Place> findById(Integer id);

    void deleteById(Integer id);

    boolean existsById(Integer id);

    double getAveragePlaceRate(Integer id);

    List<Place> getPlaceFromSelectedCity(String city);

    Set<String> getUniqueCityName();

    Place save(Place entity);
}
