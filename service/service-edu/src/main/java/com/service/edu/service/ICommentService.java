package com.service.edu.service;

import com.common.utils.PageResult;
import com.service.edu.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author wxb
 * @since 2020-05-06
 */
public interface ICommentService extends IService<Comment> {

    PageResult listByPage(Long page, Long limit, Long id);
}
