package com.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.common.utils.PageResult;
import com.service.edu.entity.Comment;
import com.service.edu.mapper.CommentMapper;
import com.service.edu.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author wxb
 * @since 2020-05-06
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Override
    public PageResult listByPage(Long page, Long limit, Long id) {
        Page<Comment> commentPage = new Page<>(page, limit);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("course_id", id);
        queryWrapper.orderByAsc("gmt_modified");
        IPage iPage = this.page(commentPage, queryWrapper);
        PageResult pageResult = PageResult.build(iPage);
        return pageResult;
    }
}
