package com.service.edu.controller;


import com.common.utils.R;
import com.service.edu.entity.Video;
import com.service.edu.entity.vo.VideoVo;
import com.service.edu.service.IVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2020-05-03
 */
@Api(description = "课时管理")
@RestController
@RequestMapping("/admin/edu/video")
public class VideoController {
    @Autowired
    private IVideoService videoService;

    @ApiOperation("根据id查询课时")
    @GetMapping("/{id}")
    public R byId(@ApiParam(value = "id", name = "id") @PathVariable("id") String id) {
        Video video = videoService.getById(id);
        return R.ok().put("video", video);
    }

    @ApiOperation("新增课时")
    @PostMapping
    public R addVideo(@ApiParam(value = "VideoVo对象", name = "video") @RequestBody VideoVo videoVo) {
        videoService.save(videoVo);
        return R.ok();
    }

    @ApiOperation("更新课时")
    @PutMapping("/{id}")
    public R updateVideo(@ApiParam(value = "VideoVo对象", name = "video") @RequestBody VideoVo videoVo,
                         @ApiParam(value = "id", name = "id") @PathVariable("id") Long id) {
        videoVo.setId(id);
        videoService.update(videoVo);
        return R.ok();
    }

    @ApiOperation("删除课时")
    @DeleteMapping("/{id}")
    public R deleteVideo(@ApiParam(value = "id", name = "id") @PathVariable("id") Long id) {
        videoService.removeById(id);
        return R.ok();
    }
}

