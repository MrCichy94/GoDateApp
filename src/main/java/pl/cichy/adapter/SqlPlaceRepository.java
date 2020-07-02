package pl.cichy.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.cichy.model.Place;
import pl.cichy.model.PlaceRepository;

@Repository
public interface SqlPlaceRepository extends PlaceRepository, JpaRepository<Place, Integer> {

    @Override
    @Query(nativeQuery = true, value ="SELECT count(*) > 0 from places where id=:id")
    boolean existsById(@Param("id") Integer id);

}
