package com.homework.service.impl;

import com.homework.common.aop.access.WebUser;
import com.homework.common.bean.BeanConvertUtils;
import com.homework.common.exception.FrontNotifiableRuntimeException;
import com.homework.common.log.LogUtils;
import com.homework.controller.vo.MovieCommentSaveVo;
import com.homework.controller.vo.MovieCommentVo;
import com.homework.controller.vo.ReturnVo;
import com.homework.dao.doc.MovieCommentDao;
import com.homework.dao.doc.UserDao;
import com.homework.dao.doc.domain.MovieCommentDoc;
import com.homework.service.MovieCommentService;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：ldy on 17/02/2018 15:08
 */
@Service
public class MovieCommentServiceImpl implements MovieCommentService {

    private static Lock lock = new ReentrantLock();
    @Autowired
    MovieCommentDao movieCommentDao;
    @Autowired
    UserDao userDao;

    @Override
    public Long saveMovieComment(MovieCommentSaveVo movieCommentSaveVo) {
        if (null == movieCommentSaveVo) {
            throw new FrontNotifiableRuntimeException(ReturnVo.ErrorCode.PARAM_INVALID.getMessage());
        }

        if (null == WebUser.getWebUserId()) {
            LogUtils.error("当前用户 userId 为空！");
            throw new FrontNotifiableRuntimeException(ReturnVo.ErrorCode.PARAM_INVALID.getMessage());
        }

        Long commentId = Math.abs(new Long(UUID.randomUUID().hashCode()));
        MovieCommentDoc movieCommentDoc = new MovieCommentDoc(commentId,
                                                                     movieCommentSaveVo.getMovieId(),
                                                                     movieCommentSaveVo.getMovieName(),
                                                                     WebUser.getWebUserId(),
                                                                     movieCommentSaveVo.getCommentContent(),
                                                                     System.currentTimeMillis(),
                                                                     new Long(0),
                                                                     new Long(0),
                                                                     System.currentTimeMillis());
        movieCommentDao.save(movieCommentDoc);
        return commentId;
    }

    @Override
    public boolean updateMovieComment(Long commentId, String commentContent) {
        MovieCommentDoc movieCommentDoc = movieCommentDao.getById(commentId);
        if (null == movieCommentDoc) {
            throw new FrontNotifiableRuntimeException("该评论不存在，无法更新！");
        }

        if (!movieCommentDoc.getUserId().equals(WebUser.getWebUserId())) {
            throw new FrontNotifiableRuntimeException("该评论不属于当前用户！");
        }

        movieCommentDoc.setCommentContent(commentContent);
        movieCommentDao.save(movieCommentDoc);
        return true;
    }

    @Override
    public boolean voteMovieComment(Long commentId) {
        //单一应用实例：使用内存并发锁即可；分布式应用：应使用全局锁
        lock.lock();
        try {
            MovieCommentDoc movieCommentDoc = movieCommentDao.getById(commentId);
            if (null == movieCommentDoc) {
                throw new FrontNotifiableRuntimeException("该评论不存在，无法点赞！");
            }

            movieCommentDoc.setVote(movieCommentDoc.getVote() + 1);
            movieCommentDao.save(movieCommentDoc);
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public boolean viewMovieComment(Long commentId) {
        //单一应用实例：使用内存并发锁即可；分布式应用：应使用全局锁
        lock.lock();
        try {
            MovieCommentDoc movieCommentDoc = movieCommentDao.getById(commentId);
            if (null == movieCommentDoc) {
                throw new FrontNotifiableRuntimeException("该评论不存在，无法查看！");
            }

            movieCommentDoc.setWatch(movieCommentDoc.getWatch() + 1);
            movieCommentDao.save(movieCommentDoc);
        } finally {
            lock.unlock();
        }
        return true;
    }

    @Override
    public List<MovieCommentVo> getMovieComment(Long movieId) {
        if (null == movieId) {
            throw new FrontNotifiableRuntimeException(ReturnVo.ErrorCode.PARAM_INVALID.getMessage());
        }

        List<String> otherCommentFilter = new ArrayList<String>(2);
        otherCommentFilter.add("movieId =");
        otherCommentFilter.add("userId !=");
        List<MovieCommentDoc> otherMovieCommentDocList = movieCommentDao.getByCondition(otherCommentFilter, movieId, WebUser.getWebUserId());

        List<String> myCommentFilter = new ArrayList<String>(2);
        myCommentFilter.add("movieId =");
        myCommentFilter.add("userId =");
        List<MovieCommentDoc> myMovieCommentDocList = movieCommentDao.getByCondition(myCommentFilter, movieId, WebUser.getWebUserId());

        //转换为MovieCommentVo列表
        List<MovieCommentVo> otherMovieCommentVoList = new ArrayList<MovieCommentVo>(otherMovieCommentDocList.size());
        for (MovieCommentDoc otherMovieCommentDoc : otherMovieCommentDocList) {
            MovieCommentVo movieCommentVo = BeanConvertUtils.convert(otherMovieCommentDoc, MovieCommentDoc.class, MovieCommentVo.class);
            String nickName = userDao.getById(movieCommentVo.getUserId()).getNickName();
            movieCommentVo.setNickName(nickName);

            otherMovieCommentVoList.add(movieCommentVo);
        }

        if (CollectionUtils.isEmpty(myMovieCommentDocList)) {
            return otherMovieCommentVoList;
        }

        MovieCommentVo myMovieCommentVo = BeanConvertUtils.convert(myMovieCommentDocList.get(0), MovieCommentDoc.class, MovieCommentVo.class);
        myMovieCommentVo.setNickName(userDao.getById(myMovieCommentVo.getUserId()).getNickName());

        List<MovieCommentVo> movieCommentVoList = ListUtils.union(Collections.singletonList(myMovieCommentVo), otherMovieCommentVoList);
        return movieCommentVoList;
    }

    @Override
    public List<MovieCommentVo> getHotMovieComment(int pageIndex, int pageSize) {
        List<String> filter = new ArrayList<String>(1);
        filter.add("createTime >=");
        List<MovieCommentDoc> movieCommentDocList = movieCommentDao.orderByConditionForPagination("-vote", pageIndex * pageSize, pageSize, filter, new Long(1));

        //转换为MovieCommentVo列表
        List<MovieCommentVo> movieCommentVoList = new ArrayList<MovieCommentVo>(movieCommentDocList.size());
        for (MovieCommentDoc movieCommentDoc : movieCommentDocList) {
            MovieCommentVo movieCommentVo = BeanConvertUtils.convert(movieCommentDoc, MovieCommentDoc.class, MovieCommentVo.class);
            String nickName = userDao.getById(movieCommentVo.getUserId()).getNickName();
            movieCommentVo.setNickName(nickName);

            movieCommentVoList.add(movieCommentVo);
        }

        return movieCommentVoList;
    }
}
