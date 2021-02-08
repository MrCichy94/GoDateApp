package pl.cichy.logic;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import pl.cichy.model.Comment;
import pl.cichy.model.Place;
import pl.cichy.model.projection.PlaceReadModel;
import pl.cichy.model.projection.PlaceWriteModel;
import pl.cichy.model.repository.CommentRepository;
import pl.cichy.model.repository.PlaceRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequestScope
public class PlaceService {

    private final CommentRepository commentRepository;
    private final PlaceRepository placeRepository;

    public PlaceService(final CommentRepository commentRepository, final PlaceRepository placeRepository) {
        this.commentRepository = commentRepository;
        this.placeRepository = placeRepository;
    }

    public List<Place> getPlaceByCity(String city) {
        return placeRepository.getPlaceFromSelectedCity(city);
    }

    public void createCommentForPlaceById(Integer id, Comment commentToAdd) {

        if (!placeRepository.existsById(id)) {
            throw new ResourceNotFoundException("No place found with id=" + id);
        } else {
            Place placeToAddComment = placeRepository.findById(id).get();

            commentToAdd.setPlace_id(id);

            Set<Comment> comments;
            comments = placeToAddComment.getComments();
            comments.add(commentToAdd);
            commentRepository.save(commentToAdd);

            Set<Comment> finalComments = comments;

            placeRepository.findById(id)
                    .ifPresent(place -> {
                        place.setComments(finalComments);
                        placeRepository.save(placeToAddComment);
                    });

            placeRepository.findById(id).ifPresent(place -> {
                place.setRate(placeRepository.getAveragePlaceRate(id));
                placeRepository.save(place);
            });
        }
    }


    public void deletePlace(Integer id) {

        placeRepository.deleteById(id);

    }

    public PlaceReadModel createPlace(final PlaceWriteModel source) {
        Place result = placeRepository.save(source.toPlace());
        return new PlaceReadModel(result);
    }

    public List<PlaceReadModel> readAll() {
        return placeRepository.findAll().stream()
                .map(PlaceReadModel::new)
                .collect(Collectors.toList());
    }
}
