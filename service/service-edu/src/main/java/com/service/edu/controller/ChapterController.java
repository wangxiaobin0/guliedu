package com.service.edu.controller;


import com.common.utils.R;
import com.service.edu.entity.Chapter;
import com.service.edu.entity.vo.ChapterVo;
import com.service.edu.service.IChapterService;
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
@Api("章节管理")
@RestController
@RequestMapping("/admin/edu/chapter")
public class ChapterController {

    @Autowired
    IChapterService chapterService;

    @ApiOperation("根据课程Id查询所有章节")
    @GetMapping("/course/{id}")
    public R listById(@ApiParam(value = "课程id", name = "id") @PathVariable("id") Long id) {
        List<ChapterVo> chapterList = chapterService.listById(id);
        return R.ok().put("list", chapterList);
    }
    @ApiOperation("根据章节Id查询")
    @GetMapping("/{id}")
    public R byId(@ApiParam(value = "章节id", name = "id") @PathVariable("id") Long id) {
        ChapterVo chapter = chapterService.byId(id);
        return R.ok().put("chapter", chapter);
    }

    @ApiOperation("新增章节")
    @PostMapping
    public R addChapter(@ApiParam(value = "ChapterVo对象", name = "chapterVo") @RequestBody Chapter chapter) {
        chapterService.save(chapter);
        return R.ok();
    }
    @ApiOperation("更新章节")
    @PutMapping("/{id}")
    public R updateChapter(@ApiParam(value = "Chapter对象", name = "chapter") @RequestBody Chapter chapter,
                           @ApiParam(value = "id", name = "id") @PathVariable("id") Long id) {
        chapter.setId(id);
        chapterService.updateById(chapter);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    public R deleteChapter(@ApiParam(value = "id", name = "id") @PathVariable("id") Long id) {
        chapterService.removeById(id);
        return R.ok();
    }
}

