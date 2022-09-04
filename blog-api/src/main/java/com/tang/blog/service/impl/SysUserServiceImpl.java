package com.tang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tang.R;
import com.tang.blog.entity.SysUser;
import com.tang.blog.entity.vo.LoginUserVo;
import com.tang.blog.mapper.SysUserMapper;
import com.tang.blog.service.LoginService;
import com.tang.blog.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tang
 * @since 2022-09-01
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    @Lazy
    private LoginService loginService;

    @Override
    public SysUser findUser(String accout, String password) {
        QueryWrapper<SysUser> wrapper=new QueryWrapper<>();
        wrapper.eq("account",accout);
        wrapper.eq("password",password);
        wrapper.select("id","account","avatar","nickname");
        SysUser user = this.getOne(wrapper);
        return user;
    }

    @Override
    public SysUser findUserByAccount(String account) {
        QueryWrapper<SysUser> wrapper=new QueryWrapper<>();
        wrapper.eq("account",account);
        SysUser user = this.getOne(wrapper);
        return user;
    }

    //根据token查询用户信息
    @Override
    public R findUserByToken(String token) {
        //1 token合法性校验
        //  是否为空，解析是否成功，redis是否存在
        SysUser sysUser=loginService.checkToken(token);
        //2 如果校验失败，返回错误
        if (sysUser==null){
            return R.error().message("token不合法");
        }
        //3 如果成功，返回结果
        LoginUserVo loginUserVo=new LoginUserVo();
        BeanUtils.copyProperties(sysUser,loginUserVo);
        return R.ok().data("loginUserVo",loginUserVo);
    }
}
