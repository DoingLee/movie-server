package com.homework.service;

import com.homework.controller.vo.ReturnVo;
import com.homework.controller.vo.UserSaveVo;
import com.homework.controller.vo.UserVo;

/**
 * @author：ldy on 12/02/2018 15:03
 */
public interface UserService {

    /**
     * 注册
     *
     * @param accountId
     * @param password
     * @return
     */
    ReturnVo register(String accountId, String password);

    /**
     * 登录
     *
     * @param accountId
     * @param password
     * @return
     */
    ReturnVo login(String accountId, String password);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    UserVo getUserInfo(Long userId);

    /**
     * 保存用户信息
     *
     * @param userSaveVo
     * @return
     */
    boolean saveAccount(UserSaveVo userSaveVo);
}
