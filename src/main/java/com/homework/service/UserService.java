package com.homework.service;

import com.homework.controller.vo.ReturnVo;
import com.homework.controller.vo.UserSaveVo;
import com.homework.controller.vo.UserVo;

/**
 * @author：ldy on 12/02/2018 15:03
 */
public interface UserService {

    ReturnVo register(String accountId, String password);

    ReturnVo login(String accountId, String password);

    UserVo getUserInfo(Long userId);

    boolean saveAccount(UserSaveVo userSaveVo);
}
