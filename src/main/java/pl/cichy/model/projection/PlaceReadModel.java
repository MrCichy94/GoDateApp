package pl.cichy.model.projection;

import pl.cichy.model.Place;

import java.util.Set;
import java.util.stream.Collectors;

public class PlaceReadModel {

    private String placeName;

    private Set<PlaceCommentReadModel> comments;

    public PlaceReadModel(Place source) {
        placeName = source.getPlaceName();
        comments = source.getComments().stream()
                .map(PlaceCommentReadModel::new)
                .collect(Collectors.toSet());
    }

    public String getPlaceName() { return placeName; }
    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public Set<PlaceCommentReadModel> getComments() { return comments; }
    public void setComments(Set<PlaceCommentReadModel> comments) { this.comments = comments; }
}
