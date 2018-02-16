package com.homework.controller;

import com.homework.common.aop.access.annotation.AccessControl;
import com.homework.controller.vo.ReturnVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：ldy on 12/02/2018 15:02
 */
@RestController
public class MovieCommentController {

    @AccessControl
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ReturnVo test() {
        return new ReturnVo.OKVo();
    }
}
