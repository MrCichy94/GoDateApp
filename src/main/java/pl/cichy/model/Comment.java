package pl.cichy.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table( name = "comments")
public class Comment {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    private int id;

    private int place_id;

    @NotBlank(message = "Nie pusty NICK!")
    private String nickName;
    @NotBlank(message = "Nie pusty KOMENTARZ!")
    private String commentText;

    @NotNull(message = "Nie zero RATE!")
    private double userRate;

    Comment(){ }

    public Comment(String nickName, String commentText, int userRate) {
        this.nickName = nickName;
        this.commentText = commentText;
        this.userRate = userRate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPlace_id() { return place_id; }
    public void setPlace_id(int place_id) { this.place_id = place_id; }

    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }

    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }

    public double getUserRate() { return userRate; }
    public void setUserRate(double userRate) { this.userRate = userRate; }

}
