package pl.cichy.model.projection;

import pl.cichy.model.Place;
import java.util.Set;
import java.util.stream.Collectors;

public class PlaceWriteModel {

    private String placeName;

    private Set<PlaceCommentWriteModel> comments;

    public String getPlaceName() { return placeName; }
    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public Set<PlaceCommentWriteModel> getComments() { return comments; }
    public void setComments(Set<PlaceCommentWriteModel> comments) { this.comments = comments; }

    public Place toPlace() {
        var result = new Place();
        result.setPlaceName(placeName);
        result.setComments(
                comments.stream()
                    .map(PlaceCommentWriteModel::toComment)
                    .collect(Collectors.toSet())
        );
        return result;
    }
}
