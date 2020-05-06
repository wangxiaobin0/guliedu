package com.service.edu.api;

import com.common.utils.PageResult;
import com.common.utils.R;
import com.service.edu.entity.vo.CourseQueryVo;
import com.service.edu.service.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 * @date 2020/5/6
 */
@Api("客户端课程管理")
@RestController("courseApi")
@RequestMapping("/api/edu/course")
public class CourseController {

    @Autowired
    ICourseService courseService;

    @ApiOperation("分页查询课程")
    @PostMapping("/{page}/{limit}")
    public R byPage(@ApiParam(value = "页数", name = "page", required = true) @PathVariable("page") Long page,
                    @ApiParam(value = "每页显示数量", name = "limit", defaultValue = "10")@PathVariable("limit") Long limit,
                    @ApiParam(value = "课程查询对象", name = "courseQueryVo") @RequestBody CourseQueryVo courseQueryVo) {
        PageResult pageResult = courseService.byPage(page, limit, courseQueryVo);
        return R.ok().put("page", pageResult);
    }
}
