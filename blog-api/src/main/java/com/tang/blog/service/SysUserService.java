package com.tang.blog.service;

import com.tang.R;
import com.tang.blog.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tang
 * @since 2022-09-01
 */
public interface SysUserService extends IService<SysUser> {

    SysUser findUser(String accout, String password);

    //根据账户查找用户
    SysUser findUserByAccount(String account);

    //根据token查询用户信息
    R findUserByToken(String token);
}
