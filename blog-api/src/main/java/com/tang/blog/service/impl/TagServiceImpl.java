package com.tang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tang.blog.entity.Tag;
import com.tang.blog.entity.vo.TagVo;
import com.tang.blog.mapper.TagMapper;
import com.tang.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.exceptionhandler.BlogException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tang
 * @since 2022-09-01
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<TagVo> findTagsByArticleId(Long articleId) {
        List<Tag> tags=baseMapper.findTagsByArticleId(articleId);

        List<TagVo> tagVoList=new ArrayList<>();
        for (Tag tag:tags){
            TagVo tagVo=new TagVo();
            BeanUtils.copyProperties(tag,tagVo);
            tagVoList.add(tagVo);
        }
        return tagVoList;
    }

    //查询热门的六个标签
    @Override
    public List<Tag> hot() {
        int limit=6;
        //标签所拥有的文章数量最多就是最热标签
        List<Long> tagIds=baseMapper.findHotTagIds(limit);
        //要的是tagId和tagName
        List<Tag> tags=new ArrayList<>();
        if (tagIds.isEmpty()){
            throw new BlogException(201,"系统异常");
        }
        for (int i = 0; i < tagIds.size(); i++) {
            System.out.println(tagIds.get(i));
            Tag tag = this.getById(tagIds.get(i));
            tags.add(tag);
        }
        return tags;
    }
}
