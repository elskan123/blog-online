package com.tang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tang.R;
import com.tang.blog.entity.Article;
import com.tang.blog.entity.SysUser;
import com.tang.blog.entity.vo.ArticleVo;
import com.tang.blog.entity.vo.TagVo;
import com.tang.blog.mapper.ArticleMapper;
import com.tang.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.blog.service.SysUserService;
import com.tang.blog.service.TagService;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    private TagService tagService;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public R listArticle(long current, long limit) {
        //创建page对象
        Page<Article> page=new Page<>(current,limit);
        //按创建时间排序
        QueryWrapper<Article> wrapper=new QueryWrapper<>();
        wrapper.orderByDesc("create_date","weight");
        Page<Article> articlePage = (Page<Article>) this.page(page, wrapper);

        List<Article> records = articlePage.getRecords();//数据list集合

        List<ArticleVo> articleVoList=new ArrayList<>();
        for (Article record :records){
            ArticleVo articleVo=new ArticleVo();
            BeanUtils.copyProperties(record,articleVo);
            //日期转换
            articleVo.setCreateDate(new DateTime(record.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
            //判断标签
            List<TagVo> tages = tagService.findTagsByArticleId(record.getId());
            if (tages!=null){
                articleVo.setTags(tages);
            }
            //判断作者
            SysUser sysUser = sysUserService.getById(record.getAuthorId());
            if (sysUser==null){
                sysUser=new SysUser();
                sysUser.setNickname("匿名用户");
            }
            articleVo.setAuthor(sysUser.getNickname());
            articleVoList.add(articleVo);
        }
        return R.ok().data("data",articleVoList);
    }
}
