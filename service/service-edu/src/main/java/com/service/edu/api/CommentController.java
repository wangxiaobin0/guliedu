package com.service.edu.api;


import com.common.entity.vo.SessionVo;
import com.common.utils.PageResult;
import com.common.utils.R;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.service.edu.entity.Comment;
import com.service.edu.feign.UCenterFeign;
import com.service.edu.service.ICommentService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2020-05-06
 */
@RestController
@RequestMapping("/api/edu/comment")
public class CommentController {

    @Autowired
    ICommentService commentService;

    @Autowired
    UCenterFeign uCenterFeign;

    @ApiOperation("课程评论列表")
    @GetMapping("/{page}/{limit}")
    public R list(@ApiParam(value = "页数", name = "page") @PathVariable("page") Long page,
                  @ApiParam(value = "显示数量", name = "limit") @PathVariable("limit") Long limit,
                  @ApiParam(value = "课程id", name = "courseId") @RequestParam("courseId") Long id) {

        PageResult commentList = commentService.listByPage(page, limit, id);
        return R.ok().put("page",commentList);
    }

    @ApiOperation("新增评论")
    @PostMapping("/auth")
    public R save(@RequestBody Comment comment, HttpServletRequest request) throws JsonProcessingException {
        SessionVo sessionVo = uCenterFeign.auth(request);
        if (sessionVo == null) {
            return R.error().code(28004);
        }
        comment.setMemberId(sessionVo.getId());
        commentService.save(comment);
        return R.ok();
    }

}