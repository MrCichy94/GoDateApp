package pl.cichy.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
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

    public void updateFrom(final Place source){
        placeName = source.placeName;
        description = source.description;
        adress = source.adress;
        coordinates = source.coordinates;
    }
}

