package com.service.edu.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.utils.R;
import com.service.edu.entity.Course;
import com.service.edu.entity.Teacher;
import com.service.edu.service.ICourseService;
import com.service.edu.service.IIndexService;
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
@RequestMapping("/api/edu/index")
public class IndexController {

    @Autowired
    IIndexService indexService;
    @GetMapping
    public R indexInfo() {
        List<Course> courseList = indexService.courseInfo();
        List<Teacher> teacherList = indexService.teacherInfo();
        return R.ok().put("courseList", courseList).put("teacherList", teacherList);
    }


}
