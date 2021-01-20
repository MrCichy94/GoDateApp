package pl.cichy.model.projection;

import pl.cichy.model.Comment;

import java.time.LocalDateTime;

//komentarz czytany w obrebie miejsca
//mozna to sobie wyobrazić jako kafelek z tytułem place'a z podpunktami komentarzy
public class PlaceCommentReadModel {

    private String nickName;
    private String commentText;
    private double userRate;
    private LocalDateTime commentDataTime;

    public PlaceCommentReadModel(Comment source) {
        nickName = source.getNickName();
        commentText = source.getCommentText();
        userRate = source.getUserRate();
        commentDataTime = source.getCommentDataTime();
    }

    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }

    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }

    public double getUserRate() { return userRate; }
    public void setUserRate(double userRate) { this.userRate = userRate; }

    public LocalDateTime getCommentDataTime() { return commentDataTime; }

    public void setCommentDataTime(LocalDateTime commentDataTime) { this.commentDataTime = commentDataTime; }
}
