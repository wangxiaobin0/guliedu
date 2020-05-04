package com.service.edu.service.impl;

import com.service.edu.entity.CourseDescription;
import com.service.edu.mapper.CourseDescriptionMapper;
import com.service.edu.service.ICourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author wxb
 * @since 2020-05-03
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements ICourseDescriptionService {

}
