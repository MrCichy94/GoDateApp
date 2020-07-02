package pl.cichy.logic;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import pl.cichy.model.Comment;
import pl.cichy.model.CommentRepository;
import pl.cichy.model.Place;
import pl.cichy.model.PlaceRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequestScope
public class PlaceServis {

    private PlaceRepository placeRepository;
    private CommentRepository commentRepository;

    PlaceServis(){ }

    public PlaceServis(PlaceRepository placeRepository, CommentRepository commentRepository) {
        this.placeRepository = placeRepository;
        this.commentRepository = commentRepository;
    }

    //nie do końca wiem jak powinno to tu wyglądać, szukamy przykładu

    public PlaceRepository getPlaceRepository() { return placeRepository; }
    public void setPlaceRepository(PlaceRepository placeRepository) { this.placeRepository = placeRepository; }

    public CommentRepository getCommentRepository() { return commentRepository; }
    public void setCommentRepository(CommentRepository commentRepository) { this.commentRepository = commentRepository; }
}
