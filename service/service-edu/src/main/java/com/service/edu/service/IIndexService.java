package com.service.edu.service;

import com.service.edu.entity.Course;
import com.service.edu.entity.Teacher;

import java.util.List;

/**
 * @author
 * @date 2020/5/5
 */
public interface IIndexService {

    /**
     * 获取首页课程信息
     * @return
     */
    List<Course> courseInfo();

    /**
     * 获取首页讲师信息
     * @return
     */
    List<Teacher> teacherInfo();
}
