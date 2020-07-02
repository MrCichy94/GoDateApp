package pl.cichy.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Place place;

    Comment(){ }

    public Comment(String nickName, String commentText) {this(nickName, commentText, null);}

    public Comment(String nickName, String commentText, Place place) {
        this.nickName = nickName;
        this.commentText = commentText;
        this.place = place;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }

    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }

    public Place getPlace() { return place; }
    public void setPlace(Place place) { this.place = place; }

}
