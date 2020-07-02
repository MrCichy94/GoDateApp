package pl.cichy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.cichy.model.Comment;
import pl.cichy.model.CommentRepository;
import pl.cichy.model.Place;
import pl.cichy.model.PlaceRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/places/{id}")
public class CommentController {

    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);
    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //POST powinien wziąć z URL id place'a
    //dodać comment do seta danego id place'a
    //albo już wcześniej zostać wrzucony do seta

    @PostMapping
    ResponseEntity<Comment> createComment(@RequestBody @Valid Comment toCreate){
        Comment result = commentRepository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }


    //GET powinien wyrzucić wszystkie komentarze danego id place'a z jego seta
    @GetMapping("/comments")
    ResponseEntity<List<Comment>> readAllComments(Pageable page){
        logger.info("Custom pageable");
        return ResponseEntity.ok(commentRepository.findAll(page).getContent());
    }


/*
    @PatchMapping
    ResponseEntity<?> updatePlace(@PathVariable int id, @RequestBody @Valid Place toUpdate,
                                  @RequestBody @Valid Comment toCreate){
        if (!placeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Comment result = commentRepository.save(toCreate);
        placeRepository.findById(id)
                .ifPresent(place -> {
                    place.updateFrom(toUpdate);
                    placeRepository.save(place);
                });
        return ResponseEntity.noContent().build();
    }
*/
}
