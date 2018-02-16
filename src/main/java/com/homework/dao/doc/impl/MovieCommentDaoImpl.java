package com.homework.dao.doc.impl;

import com.homework.common.mongo.BaseMorphiaDaoImpl;
import com.homework.dao.doc.MovieCommentDao;
import com.homework.dao.doc.domain.MovieCommentDoc;
import org.springframework.stereotype.Component;

/**
 * @author：ldy on 16/02/2018 13:09
 */
@Component("MovieCommentDao")
public class MovieCommentDaoImpl extends BaseMorphiaDaoImpl<MovieCommentDoc,Long> implements MovieCommentDao {

}
