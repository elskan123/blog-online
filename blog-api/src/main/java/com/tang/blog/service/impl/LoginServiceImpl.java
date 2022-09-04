package com.tang.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.tang.JwtUtils;
import com.tang.R;
import com.tang.blog.entity.SysUser;
import com.tang.blog.entity.vo.params.LoginParam;
import com.tang.blog.service.LoginService;
import com.tang.blog.service.SysUserService;
import com.tang.exceptionhandler.BlogException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Transactional//事务注解——数据回滚
public class LoginServiceImpl implements LoginService {
    @Lazy
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    private static final String salt = "tang!@#"; //加密盐

    //登录功能
    @Override
    public R login(LoginParam loginParam) {
        //1 检查参数是否合法
        String accout=loginParam.getAccount();
        String password = loginParam.getPassword();
        if(StringUtils.isBlank(accout)||StringUtils.isBlank(password)){
            return R.error().message("请输入账号密码");
        }
        password=DigestUtils.md5Hex(password+salt);
        //2 根据用户名和密码去user表中查询是否存在
        SysUser sysUser=sysUserService.findUser(accout,password);
        //3 如果不存在，登录失败
        if (sysUser==null){
            return R.error().message("登录失败");
        }
        //4 如果存在，使用jwt生成token，返回给前端
        String token = JwtUtils.createToken(sysUser.getId());
        //5 token放入redis当中
        redisTemplate.opsForValue().set("TOKEN"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return R.ok().data("token",token);
    }

    @Override
    public R register(LoginParam loginParam) {
        //1 判断参数是否合法
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank(account)||StringUtils.isBlank(password)||StringUtils.isBlank(nickname)){
            return R.error().message("参数不合法");
        }
        //2 判断账户是否存在，存在返回账户已经被注册
        SysUser sysUser=sysUserService.findUserByAccount(account);
        System.out.println("sssstttttttttt"+sysUser);
        if (sysUser!=null){
            throw new BlogException(20001,"账户存在，注册失败");
//            return R.error().message("账户存在，注册失败");
        }
        //3 如果不存在，注册用户
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password+salt));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(1); //1 为true
        sysUser.setDeleted(0); // 0 为false
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        this.sysUserService.save(sysUser);
        //4 生成token
        String token = JwtUtils.createToken(sysUser.getId());
        //5 存入redis，并返回
        redisTemplate.opsForValue().set("TOKEN"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return R.ok().data("token",token);
        //6 注意 加上事物，一旦中间的任何过程出现问题，注册的用户需要回滚

    }

    @Override
    public SysUser checkToken(String token) {
        if (StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> stringObjectMap = JwtUtils.checkToken(token);
        if (stringObjectMap==null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN" + token);
        if (StringUtils.isBlank(userJson)){
            return null;
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    //退出登录
    @Override
    public R logout(String token) {
        redisTemplate.delete("TOKEN"+token);
        return R.ok();
    }
}
