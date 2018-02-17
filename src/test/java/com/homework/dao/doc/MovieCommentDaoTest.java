package com.homework.dao.doc;

import com.homework.BaseTest;
import com.homework.dao.doc.domain.MovieCommentDoc;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author：ldy on 16/02/2018 13:21
 */
public class MovieCommentDaoTest extends BaseTest {

    @Autowired
    MovieCommentDao movieCommentDao;

    @Test
    public void insertOrUpdate() {
        for (int i = 0; i < 10; i++) {
            movieCommentDao.save(new MovieCommentDoc(Math.abs(new Long(UUID.randomUUID().hashCode())),
                                                            new Long(121),
                                                            "moviename" + i,
                                                            new Long(1712560897),
                                                            "评论" + i,
                                                            System.currentTimeMillis(),
                                                            new Long(i),
                                                            new Long(i),
                                                            System.currentTimeMillis()));
        }
    }

    @Test
    public void query() {
        MovieCommentDoc movieCommentDoc = movieCommentDao.getById(new Long(1941011447));
        System.out.println(movieCommentDoc.toString());
    }

    @Test
    public void queryPage() {
        List<String> properties = new ArrayList<String>();
        properties.add("movieId =");
        List<MovieCommentDoc> movieCommentList = movieCommentDao.orderByConditionForPagination("-createTime", 5, 5, properties, 121);
        for (int i = 0 ; i < movieCommentList.size();i++) {
            System.out.println(movieCommentList.get(i));
        }
    }

}