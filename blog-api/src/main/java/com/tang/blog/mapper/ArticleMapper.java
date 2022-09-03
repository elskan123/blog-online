package com.tang.blog.mapper;

import com.tang.blog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tang.blog.entity.dos.Archives;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tang
 * @since 2022-09-01
 */
public interface ArticleMapper extends BaseMapper<Article> {

    //文章归档
    List<Archives> listArchives();
}
