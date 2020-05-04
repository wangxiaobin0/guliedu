package com.service.edu.controller;


import com.common.utils.PageResult;
import com.common.utils.R;
import com.service.edu.entity.Course;
import com.service.edu.entity.vo.CoursePublishVo;
import com.service.edu.entity.vo.CourseVo;
import com.service.edu.service.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2020-05-03
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/admin/edu/course")
public class CourseController {

    @Autowired
    ICourseService courseService;

    /**
     * 课程列表
     * @return
     */
    @GetMapping
    @ApiOperation("课程列表")
    public R list() {
        List<Course> courseList = courseService.list(null);
        return R.ok().put("list", courseList);
    }

    @ApiOperation("分页查询课程")
    @GetMapping("/{page}/{limit}")
    public R byPage(@ApiParam(value = "页数", name = "page", required = true) @PathVariable("page") Long page,
                    @ApiParam(value = "每页显示数量", name = "limit", defaultValue = "10")@PathVariable("limit") Long limit) {
        PageResult pageResult = courseService.byPage(page, limit);
        return R.ok().put("page", pageResult);
    }

    @ApiOperation("根据Id查询课程")
    @GetMapping("/{id}")
    public R byId(@ApiParam(value = "id", name = "id", required = true) @PathVariable("id") Long id) {
        CourseVo courseVo = courseService.byId(id);
        return R.ok().put("course", courseVo);
    }

    @ApiOperation("发布课程")
    @PostMapping
    public R save(@ApiParam(value = "CourseVo对象", name = "course")@RequestBody CourseVo courseVo) {
        Long id = courseService.saveCourse(courseVo);
        return R.ok().put("courseId", id);
    }

    @ApiOperation("更新课程")
    @PutMapping("/{id}")
    public R update(@ApiParam(value = "course对象", name = "course")@RequestBody CourseVo courseVo,
                    @ApiParam(value = "课程id", name = "id")@PathVariable("id") Long id) {
        courseVo.setId(id);
        courseService.updateById(courseVo);
        return R.ok();
    }

    @ApiOperation("根据Id删除课程")
    @DeleteMapping("/{id}")
    public R deleteById(@ApiParam(value = "id", name = "id", required = true) @PathVariable("id") String id) {
        courseService.removeById(id);
        return R.ok();
    }

    @GetMapping("/publish/{id}")
    public R publishById(@ApiParam(value = "课程id", name = "id") @PathVariable("id") Long id) {
        CoursePublishVo coursePublishVo = courseService.publishById(id);
        return R.ok().put("coursePublishVo", coursePublishVo);
    }

    @PutMapping("/publish/{id}")
    public R publish(@ApiParam(value = "课程id", name = "id") @PathVariable("id") Long id) {
        courseService.publish(id);
        return R.ok();
    }
}

