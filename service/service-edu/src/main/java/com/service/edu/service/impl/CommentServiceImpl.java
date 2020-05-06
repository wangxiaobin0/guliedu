package com.service.edu.service.impl;

import com.service.edu.entity.Comment;
import com.service.edu.mapper.CommentMapper;
import com.service.edu.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
