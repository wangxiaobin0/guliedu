package com.service.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.service.edu.entity.Course;
import com.service.edu.entity.vo.CoursePublishVo;
import com.service.edu.entity.vo.CourseWebVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author wxb
 * @since 2020-05-03
 */
public interface CourseMapper extends BaseMapper<Course> {

    /**
     * 根据id查询发布信息
     * @param id
     * @return
     */
    CoursePublishVo publishById(@Param("id") Long id);
    /**
     * 根据id查询页面显示数据
     * @param id
     * @return
     */
    CourseWebVo detailById(@Param("id") Long id);
}
