package pl.cichy.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

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

    //@ManyToOne
    //private Place place;

    Comment(){ }

    public Comment(String nickName, String commentText) {
        this.nickName = nickName;
        this.commentText = commentText;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }

    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }

    //public Place getPlace() { return place; }

    //public void setPlace(Place place) { this.place = place; }
}
