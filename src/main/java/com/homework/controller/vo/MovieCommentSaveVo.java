package com.homework.controller.vo;

/**
 * @author：ldy on 17/02/2018 15:04
 */
public class MovieCommentSaveVo {

    Long movieId;
    String movieName;
    String commentContent;

    public MovieCommentSaveVo() {
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
}
