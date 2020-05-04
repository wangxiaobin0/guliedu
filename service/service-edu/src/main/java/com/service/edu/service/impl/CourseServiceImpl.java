package com.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.common.utils.PageResult;
import com.service.edu.constrant.CourseConstant;
import com.service.edu.entity.Course;
import com.service.edu.entity.CourseDescription;
import com.service.edu.entity.vo.CoursePublishVo;
import com.service.edu.entity.vo.CourseQueryVo;
import com.service.edu.entity.vo.CourseVo;
import com.service.edu.mapper.CourseMapper;
import com.service.edu.service.ICourseDescriptionService;
import com.service.edu.service.ICourseService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.management.relation.RoleUnresolved;
import java.util.List;


/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author wxb
 * @since 2020-05-03
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    ICourseDescriptionService descriptionService;

    @Resource
    CourseMapper courseMapper;

    @Override
    public PageResult byPage(Long page, Long limit, CourseQueryVo courseQueryVo) {
        Page<Course> coursePage = new Page<>(page, limit);
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();

        String title = courseQueryVo.getTitle();
        Long subjectId = courseQueryVo.getSubjectId();
        Long subjectParentId = courseQueryVo.getSubjectParentId();
        Long teacherId = courseQueryVo.getTeacherId();
        //创建时间顺序
        queryWrapper.orderByAsc("gmt_create");
        if (StringUtils.isNotEmpty(courseQueryVo.getTitle())) {
            queryWrapper.like("title", title);
        }
        if (subjectId != null) {
            queryWrapper.eq("subject_id", subjectId);
        }
        if (subjectParentId != null) {
            queryWrapper.eq("subject_parent_id", subjectParentId);
        }
        if (teacherId != null) {
            queryWrapper.eq("teacher_id", teacherId);
        }
        IPage<Course> iPage = this.page(coursePage, null);
        return PageResult.build(iPage);
    }

    @Override
    public CourseVo byId(Long id) {
        CourseVo vo = new CourseVo();
        Course course = this.getById(id);
        if (course == null) {
            throw new RuntimeException("该课程不存在");
        }
        BeanUtils.copyProperties(course, vo);
        CourseDescription description = descriptionService.getById(id);
        if (description != null) {
            vo.setDescription(description.getDescription());
        }
        return vo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long saveCourse(CourseVo courseVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseVo, course);
        course.setStatus(CourseConstant.STATUS_NORMAL);
        if (!this.save(course)) {
            throw new RuntimeException("课程保存失败");
        }
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(course.getId());
        courseDescription.setDescription(courseVo.getDescription());
        if (!descriptionService.save(courseDescription)){
            throw new RuntimeException("课程详情保存失败");
        }
        return course.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(CourseVo courseVo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseVo, course);
        if (!this.updateById(course)) {
            throw new RuntimeException("课程更新失败");
        }
        CourseDescription description = new CourseDescription();
        description.setId(courseVo.getId());
        description.setDescription(courseVo.getDescription());
        if (!descriptionService.updateById(description)) {
            throw new RuntimeException("课程详情更新失败");
        }
    }

    @Override
    public CoursePublishVo publishById(Long id) {
        CoursePublishVo vo = courseMapper.publishById(id);
        return vo;
    }

    @Override
    public void publish(Long id) {
        Course course = new Course();
        course.setId(id);
        course.setStatus(CourseConstant.STATUS_DRAFT);
        this.updateById(course);
    }
}
