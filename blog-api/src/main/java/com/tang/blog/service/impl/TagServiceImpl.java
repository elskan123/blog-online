package com.tang.blog.service.impl;

import com.tang.blog.entity.Tag;
import com.tang.blog.entity.vo.TagVo;
import com.tang.blog.mapper.TagMapper;
import com.tang.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
