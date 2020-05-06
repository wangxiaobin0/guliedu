package com.service.edu.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.common.utils.PageResult;
import com.common.utils.R;
import com.service.edu.entity.Course;
import com.service.edu.entity.Teacher;
import com.service.edu.service.ICourseService;
import com.service.edu.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @date 2020/5/6
 */
@Api("客户端讲师管理")
@RestController("teacherApi")
@RequestMapping("/api/edu/teacher")
public class TeacherController {

    @Autowired
    ITeacherService teacherService;

    @Autowired
    ICourseService courseService;

    @ApiOperation("讲师列表")
    @GetMapping("/{page}/{limit}")
    public R getTeacherInfo(@ApiParam(value = "", name = "", defaultValue = "1") @PathVariable("page") Long page,
                            @ApiParam(value = "", name = "", defaultValue = "8") @PathVariable("limit") Long limit) {
        PageResult<Teacher> teacher = teacherService.byPage(page, limit);
        return R.ok().put("page", teacher);
    }

    @ApiOperation("讲师详情")
    @GetMapping("/{id}")
    public R teacherDetail(@PathVariable("id") Long id){
        Teacher teacher = teacherService.getById(id);
        List<Course> courseList = courseService.courseListByTeacher(id);
        return R.ok().put("teacher", teacher).put("courseList", courseList);
    }
}
