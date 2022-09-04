package com.tang.blog.controller;

import com.tang.R;
import com.tang.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("logout")
@CrossOrigin
public class LogoutController {
    @Autowired
    private LoginService loginService;

    @GetMapping
    public R logout(@RequestHeader("Authorization") String token){
        return loginService.logout(token);
    }
}
