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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/places/{id}")
public class CommentController {

    @RequestMapping
    @ResponseBody
    public int placeIdFromUrl(@PathVariable int id) {
        int idFromUrl = id;
        return idFromUrl;
    }

    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);
    private final CommentRepository commentRepository;
    private final PlaceRepository placeRepository;

    public CommentController(CommentRepository commentRepository, PlaceRepository placeRepository) {
        this.commentRepository = commentRepository;
        this.placeRepository = placeRepository;
    }

    @PostMapping
    ResponseEntity<Comment> createComment(@RequestBody @Valid Comment toCreate){
        Comment result = commentRepository.save(toCreate);
        return ResponseEntity.created(URI.create("/comments/" + result.getId())).body(result);
    }

    //GET powinien wyrzucić wszystkie komentarze danego id place'a z jego seta
    @GetMapping("/comments")
    ResponseEntity<List<Comment>> readAllComments(Pageable page, @PathVariable String id) {
        logger.info("Custom pageable");
        return ResponseEntity.ok(commentRepository.findAll(page).getContent());
    }

    @GetMapping("/comments/{id}")
    ResponseEntity<Comment> readComment(@PathVariable int id){
        return commentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}


