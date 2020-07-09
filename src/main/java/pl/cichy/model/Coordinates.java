package pl.cichy.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table( name = "coordinates")
public class Coordinates {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;

    @NotNull(message = "Nie pusty cord x")
    private int coordinate_X;

    @NotNull(message = "Nie pusty cord y")
    private int coordinate_Y;

    public Coordinates() { }

    public Coordinates(int coordinate_X, int coordinate_Y) {
        this.coordinate_X = coordinate_X;
        this.coordinate_Y = coordinate_Y;
    }

    public int getCoordinate_X() { return coordinate_X; }
    public void setCoordinate_X(int coordinate_X) { this.coordinate_X = coordinate_X; }

    public int getCoordinate_Y() { return coordinate_Y; }
    public void setCoordinate_Y(int coordinate_Y) { this.coordinate_Y = coordinate_Y; }


}
