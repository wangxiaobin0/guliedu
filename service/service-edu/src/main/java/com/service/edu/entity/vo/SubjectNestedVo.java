package com.service.edu.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @date 2020/5/2
 */
@Data
public class SubjectNestedVo {
    private Long id;
    private String title;
    List<SubjectVo> children = new ArrayList<>();
}
