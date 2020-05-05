package com.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.service.edu.entity.Course;
import com.service.edu.entity.Teacher;
import com.service.edu.service.ICourseService;
import com.service.edu.service.IIndexService;
import com.service.edu.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @date 2020/5/5
 */
@Service
public class IndexServiceImpl implements IIndexService {
    @Autowired
    ITeacherService teacherService;
    @Autowired
    ICourseService courseService;

    @Override
    @Cacheable(value = "index", key = "getMethodName()")
    public List<Course> courseInfo() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        queryWrapper.last("limit 8");
        List<Course> courseList = courseService.list(queryWrapper);
        return courseList;
    }

    @Override
    @Cacheable(value = "index", key = "getMethodName()")
    public List<Teacher> teacherInfo() {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");
        queryWrapper.last("limit 4");
        List<Teacher> teacherList = teacherService.list(queryWrapper);
        return teacherList;
    }
}
