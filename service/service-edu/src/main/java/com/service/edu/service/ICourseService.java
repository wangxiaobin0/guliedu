package com.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.common.utils.PageResult;
import com.service.edu.entity.Course;
import com.service.edu.entity.vo.CoursePublishVo;
import com.service.edu.entity.vo.CourseQueryVo;
import com.service.edu.entity.vo.CourseVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author wxb
 * @since 2020-05-03
 */
public interface ICourseService extends IService<Course> {

    /**
     * 分页查询课程
     * @param page  页数
     * @param limit 每页显示数量
     * @return
     */
    PageResult byPage(Long page, Long limit, CourseQueryVo courseQueryVo);

    /**
     * 根据id查询CourseVo
     * @param id
     * @return
     */
    CourseVo byId(Long id);

    /**
     * 新增课程
     * @param courseVo
     * @return
     */
    Long saveCourse(CourseVo courseVo);

    /**
     * 通过id更新课程信息和课程描述
     * @param courseVo
     */
    void updateById(CourseVo courseVo);

    /**
     * 根据课程id获取发布消息
     * @param id
     * @return
     */
    CoursePublishVo publishById(Long id);

    /**
     * 发布课程
     * @param id
     */
    void publish(Long id);
}
