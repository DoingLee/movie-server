package com.homework.service.impl;

import com.homework.common.aop.access.WebUser;
import com.homework.common.constant.BaseConstant;
import com.homework.controller.vo.ReturnVo;
import com.homework.dao.doc.UserDao;
import com.homework.dao.doc.domain.UserDoc;
import com.homework.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author：ldy on 16/02/2018 20:03
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ReturnVo register(String accountId, String password) {
        if (StringUtils.isBlank(accountId) || StringUtils.isBlank(password)) {
            return new ReturnVo(ReturnVo.BusinessCode.PARAMETER_NULL.getCode(), "用户名|密码不能为空！");
        }

        //用户是否已经存在
        List<String> filter = new ArrayList<String>(1);
        filter.add("accountId =");
        List<UserDoc> userDocList = userDao.getByCondition(filter, accountId);
        if (!CollectionUtils.isEmpty(userDocList)) {
            return new ReturnVo(ReturnVo.BusinessCode.USER_ALREADY_EXIST);
        }

        //注册用户
        UserDoc userDoc = new UserDoc(Math.abs(new Long(UUID.randomUUID().hashCode())),
                                     accountId,
                                     DigestUtils.md5DigestAsHex(password.getBytes()),
                                     BaseConstant.DEFAULT,
                                     BaseConstant.DEFAULT,
                                     BaseConstant.DEFAULT,
                                     new Long(0),
                                     BaseConstant.DEFAULT,
                                     System.currentTimeMillis());
        userDao.save(userDoc);

        return new ReturnVo.OKVo();
    }

    @Override
    public ReturnVo login(String accountId, String password) {
        if (StringUtils.isBlank(accountId) || StringUtils.isBlank(password)) {
            return new ReturnVo(ReturnVo.BusinessCode.PARAMETER_NULL.getCode(), "用户名|密码不能为空！");
        }

        List<String> filter = new ArrayList<String>(1);
        filter.add("accountId =");
        List<UserDoc> userDocList = userDao.getByCondition(filter, accountId);
        if (CollectionUtils.isEmpty(userDocList)) {
            return new ReturnVo(ReturnVo.BusinessCode.USER_NOT_EXIST);
        }

        UserDoc userDoc = userDocList.get(0);
        //判断用户密码
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(userDoc.getPassword())) {
            return new ReturnVo(ReturnVo.BusinessCode.PASSWORD_INCORRECT);
        }

        //存WebUser
        WebUser.setWebUser(new WebUser(userDoc.getUserId()));

        ReturnVo returnVo = new ReturnVo.OKVo();
        returnVo.setResults(userDoc.getUserId());
        return returnVo;
    }

}
