package com.tang.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.R;
import com.tang.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.blog.entity.dos.Archives;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tang
 * @since 2022-09-01
 */
public interface ArticleService extends IService<Article> {

    R listArticle(long current, long limit);

    //首页最热文章
    List<Article> hotArticle();

    //首页最热新文章
    List<Article> newArticle();

    //文章归档
    List<Archives> listArchives();
}
