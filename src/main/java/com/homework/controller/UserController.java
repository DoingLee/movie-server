package com.homework.controller;

import com.homework.common.constant.BaseConstant;
import com.homework.common.crypto.DesUtils;
import com.homework.controller.vo.ReturnVo;
import com.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author：ldy on 12/02/2018 15:02
 */
@Controller()
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    @ResponseBody
    public ReturnVo test() {

        return new ReturnVo.OKVo();
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ReturnVo register(@RequestParam String accountId,
                             @RequestParam String password) {
        return userService.register(accountId, password);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ReturnVo login(@RequestParam String accountId,
                          @RequestParam String password,
                          HttpServletResponse response) {

        ReturnVo returnVo = userService.login(accountId, password);
        if (returnVo.getCode() != ReturnVo.ErrorCode.OK.getCode()) {
            return returnVo;
        }

        //登录成功
        Long userId = (Long) returnVo.getResults();
        returnVo.setResults(null);
        //存cookie
        Cookie cookie = new Cookie(BaseConstant.TOKEN_NAME, DesUtils.encryptToken(Long.toString(userId)));
        cookie.setPath(BaseConstant.COOKIE_PATH);
        response.addCookie(cookie);

        return returnVo;
    }

}
