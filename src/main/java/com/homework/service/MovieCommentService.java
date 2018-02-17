package com.homework.service;

import com.homework.controller.vo.MovieCommentSaveVo;
import com.homework.controller.vo.MovieCommentVo;

import java.util.List;

/**
 * @author：ldy on 12/02/2018 15:03
 */
public interface MovieCommentService {

    /**
     * 保存影评
     *
     * @param movieCommentSaveVo
     * @return commentId
     */
    Long saveMovieComment(MovieCommentSaveVo movieCommentSaveVo);

    /**
     * 更新影评
     *
     * @param commentId
     * @param commentContent
     * @return
     */
    boolean updateMovieComment(Long commentId, String commentContent);

    /**
     * 点赞影评
     *
     * @param commentId
     * @return
     */
    boolean voteMovieComment(Long commentId);

    /**
     * 查看影评
     *
     * @param commentId
     * @return
     */
    boolean viewMovieComment(Long commentId);

    /**
     * 获取某个电影的所有影评
     *
     * @param movieId
     * @return
     */
    List<MovieCommentVo> getMovieComment(Long movieId);

    /**
     * 分页获取热门影评（按点赞数倒序排列）
     *
     * @param pageIndex 0, 1, 2 ...
     * @param pageSize
     * @return
     */
    List<MovieCommentVo> getHotMovieComment(int pageIndex, int pageSize);
}
