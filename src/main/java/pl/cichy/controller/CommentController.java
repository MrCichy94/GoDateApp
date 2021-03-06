package pl.cichy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.cichy.logic.PlaceService;
import pl.cichy.model.Comment;
import pl.cichy.model.repository.CommentRepository;
import pl.cichy.model.repository.PlaceRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/places")
public class CommentController {

    @Autowired
    private PlaceService placeService;

    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
    private final CommentRepository commentRepository;
    private final PlaceRepository placeRepository;

    public CommentController(CommentRepository commentRepository, PlaceRepository placeRepository) {
        this.commentRepository = commentRepository;
        this.placeRepository = placeRepository;
    }

    @PostMapping("/{id}/comments")
    ResponseEntity<Comment> createComment(@PathVariable("id") Integer id, @RequestBody @Valid Comment toAdd){
        placeService.createCommentForPlaceById(id, toAdd);
        return ResponseEntity.created(URI.create("/" + toAdd.getId())).body(toAdd);
    }

    /* JSON-POSTMAN-TEST = POST COMMENT
    {
        "nickName": "A Przemek Foch",
        "commentText": "Do dupy!",
        "userRate": 3
    }
     */

    //GET powinien wyrzucić wszystkie komentarze danego id place'a z jego seta
    @GetMapping("/comments")
    ResponseEntity<List<Comment>> readAllComments(Pageable page) {
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


