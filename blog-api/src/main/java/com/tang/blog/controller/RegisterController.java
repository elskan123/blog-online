package com.tang.blog.controller;

import com.tang.R;
import com.tang.blog.entity.vo.params.LoginParam;
import com.tang.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("register")
@CrossOrigin
public class RegisterController {
    @Autowired
    private LoginService loginService;

    //注册
    @PostMapping
    public R register(@RequestBody LoginParam loginParam){
        loginService.register(loginParam);
        return R.ok();
    }
}
