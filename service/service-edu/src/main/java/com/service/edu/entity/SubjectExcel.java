package com.service.edu.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author
 * @date 2020/5/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectExcel {
    /**
     * 一级分类
     */
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    /**
     * 二级分类
     */
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}
