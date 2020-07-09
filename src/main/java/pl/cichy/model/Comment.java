package pl.cichy.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table( name = "comments")
public class Comment {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;

    @NotBlank(message = "Nie pusty NICK!")
    private String nickName;
    @NotBlank(message = "Nie pusty KOMENTARZ!")
    private String commentText;

    @NotNull(message = "Nie zero RATE!")
    private double userRate;

    private int place_id;

    public Comment(){ }

    public Comment(String nickName, String commentText, double userRate) {
        this.nickName = nickName;
        this.commentText = commentText;
        this.userRate = userRate;
    }
}
