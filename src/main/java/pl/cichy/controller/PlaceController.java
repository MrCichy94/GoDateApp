package pl.cichy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.cichy.logic.PlaceService;
import pl.cichy.model.Place;
import pl.cichy.model.repository.CommentRepository;
import pl.cichy.model.repository.PlaceRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/places")
class PlaceController {

    @Autowired
    private PlaceService placeService;

    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);
    private final PlaceRepository placeRepository;
    private final CommentRepository commentRepository;

    public PlaceController(PlaceRepository placeRepository,
                           CommentRepository commentRepository) {
        this.placeRepository = placeRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping
    ResponseEntity<Place> createPlace(@RequestBody @Valid Place toCreate){
        Place result = placeRepository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    /* JSON-POSTMAN-TEST = POST PLACE
    {
	    "placeName": "Aronia",
        "description": "Bardzo fajne miejsce na kawę!",
        "city": "Kraków",
        "adress": "Szewska 2",
        "coordinates": {"coordinate_X": 444, "coordinate_Y": 444}
    }
     */

    @GetMapping(params = {"!sort", "!page", "!size"})
    ResponseEntity<List<Place>> readAllPlaces(){
        logger.warn("Exposing all the places!");
        return ResponseEntity.ok(placeRepository.findAll());
    }

    @GetMapping("/pl/{city}")
    ResponseEntity<List<Place>> readPlaceByCity(@PathVariable String city){
        logger.info("Custom pageable");
        return ResponseEntity.ok(placeService.getPlaceByCity(city));
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
        if (!placeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        else {
            placeService.deletePlace(id);
        }
        return ResponseEntity.noContent().build();
    }

}
