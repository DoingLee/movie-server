package com.homework.controller;

import com.homework.common.aop.access.WebUser;
import com.homework.common.aop.access.annotation.AccessControl;
import com.homework.controller.vo.MovieCommentSaveVo;
import com.homework.controller.vo.MovieCommentVo;
import com.homework.controller.vo.ReturnVo;
import com.homework.service.MovieCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author：ldy on 12/02/2018 15:02
 */
@Controller
@RequestMapping(path = "/movie/comment")
public class MovieCommentController {

    @Autowired
    MovieCommentService movieCommentService;

    /**
     * 对某个电影添加影评
     *
     * @return
     */
    @AccessControl
    @ResponseBody
    @RequestMapping(path = "/{movieId}", method = RequestMethod.POST)
    public ReturnVo saveMovieComment(@PathVariable Long movieId, @RequestBody MovieCommentSaveVo movieCommentSaveVo) {

        movieCommentSaveVo.setMovieId(movieId);
        Long commentId = movieCommentService.saveMovieComment(movieCommentSaveVo);

        ReturnVo returnVo = new ReturnVo.OKVo();
        returnVo.setResults(commentId);
        return returnVo;
    }

    /**
     * 获取某个电影的影评
     *
     * @return
     */
    @AccessControl
    @ResponseBody
    @RequestMapping(path = "/{movieId}", method = RequestMethod.GET)
    public ReturnVo getMovieComment(@PathVariable Long movieId) {
        List<MovieCommentVo> movieCommentVoList = movieCommentService.getMovieComment(movieId);

        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (WebUser.getWebUserId().equals(movieCommentVoList.get(0).getUserId())) {
            resultMap.put("myComment", movieCommentVoList.get(0));
            movieCommentVoList.remove(0);
        } else {
            resultMap.put("myComment", null);
        }
        resultMap.put("otherComments", movieCommentVoList);

        ReturnVo returnVo = new ReturnVo.OKVo();
        returnVo.setResults(resultMap);
        return returnVo;
    }

    /**
     * 修改我的影评
     *
     * @return
     */
    @AccessControl
    @ResponseBody
    @RequestMapping(path = "/{movieId}/update", method = RequestMethod.POST)
    public ReturnVo updateMovieComment(@PathVariable Long movieId,
                                       @RequestParam Long commentId,
                                       @RequestParam String commentContent) {
        movieCommentService.updateMovieComment(commentId, commentContent);

        ReturnVo returnVo = new ReturnVo.OKVo();
        return returnVo;
    }

    /**
     * 点赞影评
     *
     * @return
     */
//    @AccessControl
    @ResponseBody
    @RequestMapping(path = "/vote/{commentId}", method = RequestMethod.POST)
    public ReturnVo voteMovieComment(@PathVariable Long commentId) {
        movieCommentService.voteMovieComment(commentId);

        ReturnVo returnVo = new ReturnVo.OKVo();
        return returnVo;
    }

    /**
     * 查看影评
     *
     * @return
     */
//    @AccessControl
    @ResponseBody
    @RequestMapping(path = "/view/{commentId}", method = RequestMethod.POST)
    public ReturnVo viewMovieComment(@PathVariable Long commentId) {
        movieCommentService.viewMovieComment(commentId);

        ReturnVo returnVo = new ReturnVo.OKVo();
        return returnVo;
    }

    /**
     * 分页获取热门影评
     *
     * @return
     */
//    @AccessControl
    @ResponseBody
    @RequestMapping(path = "/hot", method = RequestMethod.GET)
    public ReturnVo getHotMovieComment(@RequestParam Integer pageIndex,
                                       @RequestParam Integer pageSize) {
        List<MovieCommentVo> movieCommentVoList = movieCommentService.getHotMovieComment(pageIndex, pageSize);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("currentPageIndex", pageIndex);
        resultMap.put("currentPageSize", pageSize);
        resultMap.put("hot", movieCommentVoList);

        ReturnVo returnVo = new ReturnVo.OKVo();
        returnVo.setResults(resultMap);
        return returnVo;
    }
}
