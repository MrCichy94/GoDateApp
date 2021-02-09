package pl.cichy.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.cichy.model.Place;
import pl.cichy.model.repository.PlaceRepository;

import java.util.List;
import java.util.Set;

@Repository
public interface SqlPlaceRepository extends PlaceRepository, JpaRepository<Place, Integer> {

    @Override
    @Query(nativeQuery = true, value ="SELECT count(*) > 0 from places where id=:id")
    boolean existsById(@Param("id") Integer id);

    @Override
    @Query(nativeQuery = true, value ="SELECT AVG(u.USER_RATE) from COMMENTS u where place_id=:id")
    double getAveragePlaceRate(@Param("id") Integer id);

    @Override
    @Query(nativeQuery = true, value ="SELECT * from PLACES where CITY=:city ORDER BY RATE DESC ")
    List<Place> getPlaceFromSelectedCity(@Param("city") String city);

    @Override
    @Query(nativeQuery = true, value ="SELECT DISTINCT CITY from PLACES")
    Set<String> getUniqueCityName();

}
