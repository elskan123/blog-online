package com.tang.blog.service;

import com.tang.R;
import com.tang.blog.entity.SysUser;
import com.tang.blog.entity.vo.params.LoginParam;

public interface LoginService {
    //登录功能
    R login(LoginParam loginParam);

    //注册
    R register(LoginParam loginParam);

    SysUser checkToken(String token);

    //退出登录
    R logout(String token);
}
