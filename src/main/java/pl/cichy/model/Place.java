package pl.cichy.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "places")
public class Place {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;

    @NotBlank(message = "Nie pusta NAZWA!")
    private String placeName;

    @NotBlank(message = "Nie pusty opis!")
    private String description;

    @NotBlank(message = "Nie pusty adres!")
    private String adress;

    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Coordinates coordinates;

    private double rate;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Set <Comment> comments;

    public Place(){ }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlaceName() { return placeName; }
    public void setPlaceName(String placeName) { this.placeName = placeName; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getAdress() { return adress; }
    public void setAdress(String adress) { this.adress = adress; }

    public Coordinates getCoordinates() { return coordinates; }
    public void setCoordinates(Coordinates coordinates) { this.coordinates = coordinates; }

    public double getRate() { return rate; }
    public void setRate(double rate) { this.rate = rate; }

    public Set<Comment> getComments() { return comments; }
    public void setComments(Set<Comment> comments) {
        //comments.forEach(comment -> comment.setPlace(this));
        this.comments = comments;
    }

    public void updateFrom(final Place source){
        placeName = source.placeName;
        description = source.description;
        adress = source.adress;
        coordinates = source.coordinates;
    }

}

