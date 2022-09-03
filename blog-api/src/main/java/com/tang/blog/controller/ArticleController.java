package com.tang.blog.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.R;
import com.tang.blog.entity.Article;
import com.tang.blog.entity.vo.params.PageParams;
import com.tang.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("article")
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 分页查询 文章列表
     * @param pageParams
     * @return
     */
    @PostMapping
    public R listArticle(@RequestBody PageParams pageParams){
        R r = articleService.listArticle(pageParams.getPage(),pageParams.getPageSize());
        System.out.println(r);
        return r;
    }

}

