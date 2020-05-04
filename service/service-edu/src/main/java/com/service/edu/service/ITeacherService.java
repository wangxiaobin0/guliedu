package com.service.edu.service;

import com.common.utils.PageResult;
import com.service.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.service.edu.entity.vo.TeacherVo;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author wxb
 * @since 2020-05-01
 */
public interface ITeacherService extends IService<Teacher> {

    /**
     * 分页查询讲师
     * @param page  页数
     * @param limit 每页显示数量
     * @return
     */
    PageResult byPage(Long page, Long limit);

    List<TeacherVo> list();
}
