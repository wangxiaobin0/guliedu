package com.service.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.utils.R;
import com.service.edu.entity.Course;
import com.service.edu.entity.Teacher;
import com.service.edu.service.ICourseService;
import com.service.edu.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author
 * @date 2020/5/4
 */
@Api("首页信息")
@RestController
@RequestMapping("/eduservice/index")
public class IndexController {

    @Autowired
    ITeacherService teacherService;
    @Autowired
    ICourseService courseService;

    @GetMapping
    public R indexInfo() {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.orderByAsc("id");
        courseQueryWrapper.last("limit 8");
        List<Course> courseList = courseService.list(courseQueryWrapper);

        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.orderByAsc("id");
        courseQueryWrapper.last("limit 4");
        List<Teacher> teacherList = teacherService.list(teacherQueryWrapper);
        return R.ok().put("courseList", courseList).put("teacherList", teacherList);
    }
}
