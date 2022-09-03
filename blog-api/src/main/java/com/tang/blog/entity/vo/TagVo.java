package com.tang.blog.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class TagVo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String tagName;
}
