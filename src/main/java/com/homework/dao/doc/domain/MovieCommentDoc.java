package com.homework.dao.doc.domain;

import com.homework.common.mongo.domain.DocumentBaseDomain;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * @author：ldy on 16/02/2018 12:58
 */
@Entity(value = "movie_comment_doc", noClassnameStored = true)
public class MovieCommentDoc extends DocumentBaseDomain {

    @Id
    Long commentId;
    Long movieId;
    String movieName;
    Long userId;
    String commentContent;
    Long commentTime;
    Long watch;
    Long vote;
    Long createTime;

    public MovieCommentDoc() {
    }

    public MovieCommentDoc(Long commentId, Long movieId, String movieName, Long userId, String commentContent, Long commentTime, Long watch, Long vote, Long createTime) {
        this.commentId = commentId;
        this.movieId = movieId;
        this.movieName = movieName;
        this.userId = userId;
        this.commentContent = commentContent;
        this.commentTime = commentTime;
        this.watch = watch;
        this.vote = vote;
        this.createTime = createTime;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "MovieCommentDoc{" +
                       "commentId=" + commentId +
                       ", movieId=" + movieId +
                       ", movieName='" + movieName + '\'' +
                       ", userId=" + userId +
                       ", commentContent='" + commentContent + '\'' +
                       ", commentTime=" + commentTime +
                       ", watch=" + watch +
                       ", vote=" + vote +
                       ", createTime=" + createTime +
                       '}';
    }
}
