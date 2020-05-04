package com.service.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author
 * @date 2020/5/3
 */
@Data
public class CoursePublishVo {

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "一级分类名称")
    private String subjectLevelOne;

    @ApiModelProperty(value = "二级分类名称")
    private String subjectLevelTwo;

    @ApiModelProperty(value = "讲师名称")
    private String teacherName;

}
