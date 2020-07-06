package pl.cichy.controller;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.cichy.model.Comment;
import pl.cichy.model.CommentRepository;
import pl.cichy.model.Place;
import pl.cichy.model.PlaceRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/places")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);
    private final CommentRepository commentRepository;
    private final PlaceRepository placeRepository;

    public CommentController(CommentRepository commentRepository, PlaceRepository placeRepository) {
        this.commentRepository = commentRepository;
        this.placeRepository = placeRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/comments")
    public void createComment(@PathVariable("id") Integer id, @RequestBody Comment toCreate){
        Place place1 = placeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No place found with id="+id));
        toCreate.setPlace_id(id);

        Set <Comment> comments = new HashSet<>();
        comments = place1.getComments();
        comments.add(toCreate);
        commentRepository.save(toCreate);

        Set<Comment> finalComments = comments;

        placeRepository.findById(id)
                .ifPresent(place -> {
                    place.setComments(finalComments);
                    placeRepository.save(place1);
                });

        placeRepository.findById(id).ifPresent(place -> {
            place.setRate(placeRepository.getAveragePlaceRate(id));
            placeRepository.save(place);
        });

        logger.info("Comment added");
        ResponseEntity.created(URI.create("/" + toCreate.getId())).body(toCreate);
    }

    //GET powinien wyrzucić wszystkie komentarze danego id place'a z jego seta
    @GetMapping("/{id}/comments")
    ResponseEntity<List<Comment>> readAllComments(Pageable page, @PathVariable String id) {
        logger.info("Custom pageable");
        return ResponseEntity.ok(commentRepository.findAll(page).getContent());
    }

    @GetMapping("/{id}/comments/{id}")
    ResponseEntity<Comment> readComment(@PathVariable int id){
        return commentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}


