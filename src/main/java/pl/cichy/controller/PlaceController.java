package pl.cichy.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.cichy.model.Comment;
import pl.cichy.model.CommentRepository;
import pl.cichy.model.Place;
import pl.cichy.model.PlaceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/places")
class PlaceController {

    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);
    private final PlaceRepository placeRepository;

    public PlaceController(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @PostMapping
    ResponseEntity<Place> createPlace(@RequestBody @Valid Place toCreate){
        Place result = placeRepository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Place>> readAllPlaces(){
        logger.warn("Exposing all the places!");
        return ResponseEntity.ok(placeRepository.findAll());
    }

    @GetMapping
    ResponseEntity<List<Place>> readAllPlaces(Pageable page){
        logger.info("Custom pageable");
        return ResponseEntity.ok(placeRepository.findAll(page).getContent());
    }

    @GetMapping("/{id}")
    ResponseEntity<Place> readPlaces(@PathVariable int id){
        return placeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updatePlace(@PathVariable int id, @RequestBody @Valid Place toUpdate){
        if (!placeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        placeRepository.findById(id)
                .ifPresent(place -> {
                    place.updateFrom(toUpdate);
                    placeRepository.save(place);
                });
        return ResponseEntity.noContent().build();
    }

    //pomyślimy czy to tu będzie, i dla kogo
    @DeleteMapping("/{id}")
    ResponseEntity<Place> deletePlace(@PathVariable int id) {
        if (placeRepository.existsById(id)) {
            placeRepository.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }

}
