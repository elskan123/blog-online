package com.tang.blog.controller;


import com.tang.R;
import com.tang.blog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tang
 * @since 2022-09-01
 */
@RestController
@RequestMapping("users")
@CrossOrigin
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    //根据token查询用户信息
    @GetMapping("currentUser")
    public R currentUser(@RequestHeader("Authorization") String token){
        R userByToken = sysUserService.findUserByToken(token);
        return userByToken;
    }

}

