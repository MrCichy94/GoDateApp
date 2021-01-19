package pl.cichy.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cichy.model.Coordinates;
import pl.cichy.model.repository.CoordinatesRepository;

@Repository
public interface SqlCoordinatesRepository extends CoordinatesRepository, JpaRepository<Coordinates, Integer> {


}
