package com.tang.blog.service;

import com.tang.blog.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.blog.entity.vo.TagVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tang
 * @since 2022-09-01
 */
public interface TagService extends IService<Tag> {
    List<TagVo> findTagsByArticleId(Long articleId);

    //查询热门的六个标签
    List<Tag> hot();
}
