package com.tang.blog.controller;


import com.tang.R;
import com.tang.blog.entity.Tag;
import com.tang.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tang
 * @since 2022-09-01
 */
@RestController
@RequestMapping("tag")
@CrossOrigin
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 查询热门的六个标签
     * @return
     */
    @GetMapping("hot")
    public R hot(){
        List<Tag> list=tagService.hot();
        return R.ok().data("list",list);
    }

}

