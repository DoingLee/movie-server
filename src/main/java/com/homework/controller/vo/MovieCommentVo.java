package com.homework.controller.vo;

/**
 * @author：ldy on 17/02/2018 15:14
 */
public class MovieCommentVo {
    Long commentId;
    Long movieId;
    String movieName;
    String commentContent;
    Long commentTime;
    Long watch;
    Long vote;
    Long userId;

    String nickName;
    String accountId;

    public MovieCommentVo() {
    }

    public MovieCommentVo(Long commentId, Long movieId, String movieName, String commentContent, Long commentTime, Long watch, Long vote, Long userId, String nickName, String accountId) {
        this.commentId = commentId;
        this.movieId = movieId;
        this.movieName = movieName;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.watch = watch;
        this.vote = vote;
        this.userId = userId;
        this.nickName = nickName;
        this.accountId = accountId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Long getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Long commentTime) {
        this.commentTime = commentTime;
    }

    public Long getWatch() {
        return watch;
    }

    public void setWatch(Long watch) {
        this.watch = watch;
    }

    public Long getVote() {
        return vote;
    }

    public void setVote(Long vote) {
        this.vote = vote;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
