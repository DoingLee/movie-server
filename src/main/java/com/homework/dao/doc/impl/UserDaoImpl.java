package com.homework.dao.doc.impl;

import com.homework.common.mongo.BaseMorphiaDaoImpl;
import com.homework.dao.doc.UserDao;
import com.homework.dao.doc.domain.UserDoc;
import org.springframework.stereotype.Component;

/**
 * @author：ldy on 16/02/2018 13:10
 */
@Component("UserDao")
public class UserDaoImpl extends BaseMorphiaDaoImpl<UserDoc, Long> implements UserDao {
}
