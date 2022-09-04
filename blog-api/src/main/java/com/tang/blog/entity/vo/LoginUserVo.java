package com.tang.blog.entity.vo;

import lombok.Data;

@Data
public class LoginUserVo {

    private Long id;

    private String account;

    private String nickname;

    private String avatar;
}
