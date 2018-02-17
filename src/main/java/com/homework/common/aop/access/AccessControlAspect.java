package com.homework.common.aop.access;

import com.homework.common.constant.BaseConstant;
import com.homework.common.crypto.DesUtils;
import com.homework.common.exception.FrontNotifiableRuntimeException;
import com.homework.common.log.LogUtils;
import com.homework.dao.doc.UserDao;
import com.homework.dao.doc.domain.UserDoc;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author：ldy on 16/02/2018 17:58
 */
@Aspect
@Component
public class AccessControlAspect {

    @Autowired
    UserDao userDao;

    @Pointcut(value = "@annotation(com.homework.common.aop.access.annotation.AccessControl)")
    public void accessControl() {
    }

    @Before("accessControl()")
    public void before() throws IOException {
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        Cookie[] cookies = httpServletRequest.getCookies();

        //cookie中无token
        if (null == cookies || cookies.length == 0) {
            LogUtils.debug("cookie中无token");
            throw new FrontNotifiableRuntimeException("该接口需要登录!");
        }

        for (Cookie cookie : cookies) {
            if (!cookie.getName().equals(BaseConstant.TOKEN_NAME)) {
                continue;
            }
            String token = cookie.getValue();
            LogUtils.debug("用户raw token：" + token);
            String userId = DesUtils.decryptToken(token);

            //token为空
            if (StringUtils.isEmpty(token)) {
                LogUtils.debug("token为空");
                cookie.setMaxAge(0); //清除cookie
                cookie.setPath(BaseConstant.COOKIE_PATH);
                httpServletResponse.addCookie(cookie);
                throw new FrontNotifiableRuntimeException("该接口需要登录!");
            }

            LogUtils.debug("用户token（userId）：" + userId);

            //判断用户是否存在
            UserDoc userDoc = userDao.getById(Long.parseLong(userId));
            if (null == userDoc) {
                LogUtils.debug("token(userId) " + userId + " 不存在！");
                cookie.setMaxAge(0); //清除cookie
                cookie.setPath(BaseConstant.COOKIE_PATH);
                httpServletResponse.addCookie(cookie);
                throw new FrontNotifiableRuntimeException("该接口需要登录!");
            }

            //用户存在，保存用户信息到ThreadLocal
            LogUtils.debug("token验证通过");
            WebUser.setWebUser(new WebUser(userDoc.getUserId()));
            return;
        }

        LogUtils.debug("cookie中无token");
        throw new FrontNotifiableRuntimeException("该接口需要登录!");

    }


}
