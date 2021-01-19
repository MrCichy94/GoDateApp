package pl.cichy.model.projection;

import pl.cichy.model.Comment;

import java.time.LocalDateTime;

//To jest nasz DTO
public class PlaceCommentWriteModel {

    private String nickName;
    private String commentText;
    private double userRate;
    private LocalDateTime commentDataTime;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public double getUserRate() {
        return userRate;
    }

    public void setUserRate(double userRate) {
        this.userRate = userRate;
    }

    public LocalDateTime getCommentDataTime() {
        return commentDataTime;
    }

    public void setCommentDataTime(LocalDateTime commentDataTime) {
        this.commentDataTime = commentDataTime;
    }

    public Comment toComment() {
        return new Comment(nickName, commentText, userRate, commentDataTime);
    }
}
