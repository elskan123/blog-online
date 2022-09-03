package com.tang.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.R;
import com.tang.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
