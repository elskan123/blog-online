package com.tang.blog.controller;

import com.tang.R;
import com.tang.blog.entity.vo.params.LoginParam;
import com.tang.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public R login(@RequestBody LoginParam loginParam){
        //登录 验证用户 访问用户表
        return loginService.login(loginParam);
    }
}
