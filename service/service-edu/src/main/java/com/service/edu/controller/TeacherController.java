package com.service.edu.controller;


import com.common.utils.PageResult;
import com.common.utils.R;
import com.service.edu.entity.Teacher;
import com.service.edu.entity.vo.TeacherVo;
import com.service.edu.service.ITeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2020-05-01
 */
@Api(value = "/edu", description = "讲师管理")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;


    @ApiOperation("所有讲师")
    @GetMapping
    public R list() {
        List<TeacherVo> list = teacherService.list();
        return R.ok().put("list", list);
    }

    @ApiOperation("分页查询讲师")
    @GetMapping("/{page}/{limit}")
    public R byPage(@ApiParam(name = "page", value = "页码", required = true) @PathVariable("page") Long page,
                    @ApiParam(name = "limit", value = "每页显示数量", required = true)@PathVariable("limit") Long limit){
        PageResult<Teacher> teacherList = teacherService.byPage(page, limit);
        return R.ok().put("page", teacherList);
    }

    @ApiOperation("根据ID查询讲师")
    @GetMapping("/{id}")
    public R byId(@ApiParam(value = "讲师ID", name = "id", required = true)@PathVariable("id") Long id) {
        Teacher teacher = teacherService.getById(id);
        return R.ok().put("teacher", teacher);
    }

    @PostMapping
    @ApiOperation("新增讲师")
    public R add(@ApiParam(name = "teacher", value = "讲师对象",required = true) @RequestBody Teacher teacher){
        boolean save = teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation("更新讲师信息")
    @PutMapping("/{id}")
    public R update(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable("id") Long id,
            @ApiParam(name = "teacher", value = "讲师对象",required = true) @RequestBody Teacher teacher) {
        teacher.setId(id);
        boolean update = teacherService.updateById(teacher);
        return R.ok();
    }

    @ApiOperation("根据id删除讲师")
    @DeleteMapping("/{id}")
    public R deleteById(@PathVariable("id") Long id) {
        teacherService.removeById(id);
        return R.ok();
    }
}

