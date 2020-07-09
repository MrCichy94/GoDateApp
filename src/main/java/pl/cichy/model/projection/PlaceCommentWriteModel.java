package pl.cichy.model.projection;

import pl.cichy.model.Comment;

//To jest nasz DTO
public class PlaceCommentWriteModel {

    private String nickName;
    private String commentText;
    private double userRate;

    public String getNickName() { return nickName; }
    public void setNickName(String nickName) { this.nickName = nickName; }

    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }

    public double getUserRate() { return userRate; }
    public void setUserRate(double userRate) { this.userRate = userRate; }

    public Comment toComment(){
        return new Comment(nickName, commentText, userRate);
    }
}
