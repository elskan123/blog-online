package com.tang.blog.mapper;

import com.tang.blog.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tang
 * @since 2022-09-01
 */
public interface TagMapper extends BaseMapper<Tag> {

    //根据文章id查询标签列表
    List<Tag> findTagsByArticleId(Long articleId);

    //查询热门的六个标签
    List<Long> findHotTagIds(int limit);
}
