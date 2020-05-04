package com.service.edu.entity.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @date 2020/5/4
 */
@Data
public class CourseQueryVo {
    @ApiModelProperty("课程标题")
    private String title;
    @ApiModelProperty("父级id")
    private Long subjectParentId;
    @ApiModelProperty("分类id")
    private Long subjectId;
    @ApiModelProperty("讲师id")
    private Long teacherId;
}
