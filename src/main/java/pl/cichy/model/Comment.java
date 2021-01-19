package pl.cichy.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;

    private int place_id;

    private LocalDateTime commentDataTime = LocalDateTime.now();

    @NotBlank(message = "Nie pusty NICK!")
    private String nickName;
    @NotBlank(message = "Nie pusty KOMENTARZ!")
    private String commentText;

    @NotNull(message = "Nie zero RATE!")
    private double userRate;

    public Comment() {
    }

    public Comment(String nickName, String commentText, double userRate, LocalDateTime commentDataTime) {
        this.nickName = nickName;
        this.commentText = commentText;
        this.userRate = userRate;
        this.commentDataTime = commentDataTime;
    }
}
