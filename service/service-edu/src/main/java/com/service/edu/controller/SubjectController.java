package com.service.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.common.utils.R;
import com.service.edu.entity.vo.SubjectNestedVo;
import com.service.edu.service.ISubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author
 * @date 2020/5/2
 */
@Api(description = "课程分类管理")
@RestController
@RequestMapping("/admin/edu/subject")
public class SubjectController {

    @Autowired
    ISubjectService subjectService;

    /**
     * 导入
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/import")
    @ApiOperation("导入Excel文件")
    public R importExcel(@ApiParam(name = "file", value = "Excel文件") @RequestParam("file") MultipartFile file) throws IOException {
        subjectService.importExcel(file);
        return R.ok();
    }

    @GetMapping
    public R list(){
        List<SubjectNestedVo> list = subjectService.listSubjectNestedVo(new QueryWrapper<>());
        return R.ok().put("list", list);
    }


}
